<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.Contact" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contacts List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body class="bg-light">

<jsp:include page="navbar.jsp" />

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary">Contact List</h2>
        <a href="contacts?action=new" class="btn btn-success">+ Add New Contact</a>
    </div>

    <% 
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <div class="table-responsive shadow rounded bg-white p-3">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-primary">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone Number</th>
                    <th>Email Address</th>
                    <th>ID Number</th>
                    <th>Date Of Birth</th>
                    <th>Gender</th>
                    <th>County</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Contact> contactList = (List<Contact>) request.getAttribute("contactList");
                    if (contactList != null) {
                        for (Contact c : contactList) {
                %>
                <tr>
                    <td><%= c.getFirstName() %></td>
                    <td><%= c.getLastName() %></td>
                    <td><%= c.getPhoneNumber() %></td>
                    <td><%= c.getEmailAddress() %></td>
                    <td><%= c.getIdNumber() %></td>
                    <td><%= c.getDateOfBirth() %></td>
                    <td><%= c.getGender() %></td>
                    <td><%= c.getCounty() %></td>
                    <td class="d-flex gap-2 flex-wrap">
                        <a href="contacts?action=edit&id=<%= c.getContact_id() %>" class="btn btn-sm btn-outline-primary">Edit</a>
                        <a href="contacts?action=delete&id=<%= c.getContact_id() %>" 
                           class="btn btn-sm btn-outline-danger"
                           onclick="return confirm('Are you sure you want to delete this contact?');">Delete</a>
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="9" class="text-center text-muted">No contacts available.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
