package com.contactregistry.ContactRegistryApp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.contactregistry.ContactRegistryApp.dao.ContactDAO;
import com.contactregistry.ContactRegistryApp.model.Contact;
import com.contactregistry.ContactRegistryApp.util.DBUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String county = req.getParameter("county");
        try (Connection conn = DBUtil.getConnection()) {
            ContactDAO contactDao = new ContactDAO(conn);
            List<Contact> contactList;
            if (county != null && !county.isEmpty()) {
                contactList = contactDao.getContactsByCounty(county);
            } else {
                contactList = contactDao.getAllContacts();
            }

            List<String> counties = contactDao.getAllCounties();

            req.setAttribute("contacts", contactList);
            req.setAttribute("counties", counties);
            req.setAttribute("selectedCounty", county);

            RequestDispatcher dispatcher = req.getRequestDispatcher("report.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
