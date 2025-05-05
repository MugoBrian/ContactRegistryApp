package com.contactregistry.ContactRegistryApp.controller;

import java.io.IOException;
import java.sql.Connection;

import com.contactregistry.ContactRegistryApp.dao.UserDAO;
import com.contactregistry.ContactRegistryApp.model.User;
import com.contactregistry.ContactRegistryApp.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email_address = req.getParameter("email_address");
        String password = req.getParameter("password");

        try (Connection conn = DBUtil.getConnection()) {
            UserDAO userDao = new UserDAO(conn);
            User user = userDao.validate(email_address, password);

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect("dashboard.jsp");
            } else {
                req.setAttribute("error", "Invalid credentials");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

}
