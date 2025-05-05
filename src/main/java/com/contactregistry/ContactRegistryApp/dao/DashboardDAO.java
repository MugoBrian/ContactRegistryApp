package com.contactregistry.ContactRegistryApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardDAO {

    private final Connection conn;

    public DashboardDAO(Connection conn) {
        this.conn = conn;
    }

    public Map<String, Integer> getGenderStats() throws SQLException {
        Map<String, Integer> genderStats = new HashMap<>();
        
        String sql = "SELECT gender, COUNT(*) AS total FROM contacts GROUP BY gender";
        
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                genderStats.put(rs.getString("gender"), rs.getInt("total"));
            }
        }
        return genderStats;
    }

    public Map<String, Integer> getCountyStats() throws SQLException {
        Map<String, Integer> countyStats = new HashMap<>();
        String sql = "SELECT county, COUNT(*) AS total FROM contacts GROUP BY county";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                countyStats.put(rs.getString("county"), rs.getInt("total"));
            }
        }
        return countyStats;
    }

    public List<Map<String, String>> getRecentContacts() throws SQLException {
        List<Map<String, String>> recentContacts = new ArrayList<>();
        String sql = "SELECT first_name, last_name, email_address, phone_number, created_at FROM contacts ORDER BY created_at DESC LIMIT 5";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("first_name", rs.getString("first_name"));
                row.put("last_name", rs.getString("last_name"));
                row.put("email_address", rs.getString("email_address"));
                row.put("phone_number", rs.getString("phone_number"));
                row.put("created_at", rs.getString("created_at"));
                recentContacts.add(row);
            }
        }
        return recentContacts;
    }
}
