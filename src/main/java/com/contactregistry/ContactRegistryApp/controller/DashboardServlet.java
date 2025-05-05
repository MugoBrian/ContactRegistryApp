package com.contactregistry.ContactRegistryApp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.contactregistry.ContactRegistryApp.dao.DashboardDAO;
import com.contactregistry.ContactRegistryApp.util.DBUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try (Connection conn = DBUtil.getConnection()) {
            DashboardDAO dashboardDao = new DashboardDAO(conn);
            Map<String, Integer> genderStats = dashboardDao.getGenderStats();
            Map<String, Integer> countyStats = dashboardDao.getCountyStats();
            List<Map<String, String>> recentContacts = dashboardDao.getRecentContacts();
            
            System.out.println("GenderStats" + genderStats);

            req.setAttribute("genderStats", genderStats);
            req.setAttribute("countyStats", countyStats);
            req.setAttribute("recentContacts", recentContacts);

            RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
