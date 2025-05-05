package com.contactregistry.ContactRegistryApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.contactregistry.ContactRegistryApp.model.Contact;

// full_name  
// phone_number 
// email_address
// id_number 
// date_of_birth
// gender     
// county 

public class ContactDAO {

    private Connection conn;

    private ContactDAO(Connection conn) {
        this.conn = conn;
    }

    public void addContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts(first_name, last_name, phone_number, email_address, id_number, date_of_birth, gender, county) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhoneNumber());
            statement.setString(4, contact.getEmailAddress());
            statement.setInt(5, contact.getIdNumber());
            statement.setDate(6, Date.valueOf(contact.getDateOfBirth()));
            statement.setString(7, contact.getGender());
            statement.setString(8, contact.getCounty());

            statement.executeUpdate();
        }
    }

    public List<Contact> getAllContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt(("id")));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setPhoneNumber(rs.getString("phone_number"));
                contact.setEmailAddress(rs.getString("email"));
                contact.setIdNumber(rs.getInt("id_number"));
                contact.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                contact.setGender(rs.getString("gender"));
                contact.setCounty(rs.getString("county"));

                contacts.add(contact);

            }
        }
        return contacts;

    }

    public Contact getContactById(int id) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Contact contact = new Contact();

                contact.setId(rs.getInt("id"));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setPhoneNumber(rs.getString("phone"));
                contact.setEmailAddress(rs.getString("email"));
                contact.setIdNumber(rs.getInt("id_number"));
                contact.setDateOfBirth(rs.getDate("dob").toLocalDate());
                contact.setGender(rs.getString("gender"));
                contact.setCounty(rs.getString("county"));

                return contact;
            }
        }
        return null;
    }

    public void updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET full_name = ?, phone = ?, email = ?, id_number = ?, dob = ?, gender = ?, county = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhoneNumber());
            statement.setString(4, contact.getEmailAddress());
            statement.setInt(5, contact.getIdNumber());
            statement.setDate(6, Date.valueOf(contact.getDateOfBirth()));
            statement.setString(7, contact.getGender());
            statement.setString(8, contact.getCounty());

            statement.executeUpdate();
        }
    }

    public void deleteContact(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Contact> getContactsByCounty(String county) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contacts WHERE county = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, county);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setPhoneNumber(rs.getString("phone"));
                contact.setEmailAddress(rs.getString("email"));
                contact.setIdNumber(rs.getInt("id_number"));
                contact.setDateOfBirth(rs.getDate("dob").toLocalDate());
                contact.setGender(rs.getString("gender"));
                contact.setCounty(rs.getString("county"));

                contacts.add(contact);
            }
        }

        return contacts;
    }

    public List<String> getAllCounties() throws SQLException {
        List<String> counties = new ArrayList<>();
        String sql = "SELECT DISTINCT county FROM contacts";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                counties.add(rs.getString("county"));
            }
        }

        return counties;
    }

}
