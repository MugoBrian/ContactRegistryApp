package com.contactregistry.ContactRegistryApp.controller;

import com.contactregistry.ContactRegistryApp.dao.ContactDAO;
import com.contactregistry.ContactRegistryApp.model.Contact;
import com.contactregistry.ContactRegistryApp.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@jakarta.servlet.annotation.WebServlet("/contacts")
public class ContactServlet extends HttpServlet {

    private ContactDAO contactDAO;

    @Override
    public void init() {
        try {
            Connection conn = DBUtil.getConnection();
            contactDAO = new ContactDAO(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action == null ? "list" : action) {
                case "form" -> showForm(request, response);
                default -> listContacts(request, response);
            }
        } catch (ServletException | IOException | SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listContacts(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Contact> contacts = contactDAO.getAllContacts();
        request.setAttribute("contactList", contacts);
        request.getRequestDispatcher("contact-list.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            int contact_id = Integer.parseInt(id);
            Contact existing = contactDAO.getContactById(contact_id);
            request.setAttribute("contact", existing);
            request.getRequestDispatcher("contact-form.jsp").forward(request, response);

        } else {

            request.getRequestDispatcher("contact-form.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");

        switch (method) {
            case "put":
                updateContact(req, resp);
                break;

            case "delete":
                deleteContact(req, resp);

            default:
                Contact contact = extractContactFromRequest(req);
                try {
                    contactDAO.addContact(contact);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("contacts");
        }

    }

    private Contact extractContactFromRequest(HttpServletRequest request) {
        Contact contact = new Contact();
        contact.setFirstName(request.getParameter("first_name"));
        contact.setLastName(request.getParameter("last_name"));
        contact.setPhoneNumber(request.getParameter("phone_number"));
        contact.setEmailAddress(request.getParameter("email_address"));
        contact.setIdNumber(Integer.parseInt(request.getParameter("id_number")));
        contact.setDateOfBirth((LocalDate.parse(request.getParameter("date_of_birth"))));
        contact.setGender(request.getParameter("gender"));
        contact.setCounty(request.getParameter("county"));

        return contact;
    }

    protected void deleteContact(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            contactDAO.deleteContact(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("contacts");
    }

    protected void updateContact(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Contact contact = extractContactFromRequest(req);
        contact.setId(Integer.parseInt(req.getParameter("contact_id")));
        try {
            contactDAO.updateContact(contact);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("contacts");
    }

}
