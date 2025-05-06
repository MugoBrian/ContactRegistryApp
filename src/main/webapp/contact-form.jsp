<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.contactregistry.ContactRegistryApp.model.Contact" %>
<%
    Contact contact = (Contact) request.getAttribute("contact");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body class="bg-light">
<jsp:include page="navbar.jsp" />
<div class="container mt-4">
    <h2><%= contact != null ? "Edit Contact" : "Add New Contact" %></h2>
    <% 
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="alert alert-danger mt-4 mb-4"><%= error %></div>
    <% } %>
    <form action="contacts" method="post" class="row g-3 mt-4">
        <% if (contact != null) { %>
            <input type="hidden" name="id" value="<%= contact.getId() %>" />
            <input type="hidden" name="_method" value="put" />
        <% } %>
        <div class="row">
            <div class="col-md-6 col-lg-6">
                <label class="form-label">First Name</label>
                <input type="text" name="first_name" class="form-control" value="<%= contact != null ? contact.getFirstName() : "" %>" required>
            </div>
            <div class="col-md-6 col-lg-6">
                <label class="form-label">Last Name</label>
                <input type="text" name="last_name" class="form-control" value="<%= contact != null ? contact.getLastName() : "" %>" required>
            </div>
        </div>

        <div class="col-md-6 col-lg-12">
            <label class="form-label">Phone Number</label>
            <input type="text" name="phone_number" minlength="10" class="form-control" value="<%= contact != null ? contact.getPhoneNumber() : "" %>" required>
        </div>
        <div class="col-md-6 col-lg-12">
            <label class="form-label">Email Address</label>
            <input type="email" name="email_address" class="form-control" value="<%= contact != null ? contact.getEmailAddress() : "" %>" required>
        </div>
        <div class="col-md-6 col-lg-12">
            <label class="form-label">ID Number</label>
            <input type="text" name="id_number" class="form-control" value="<%= contact != null ? contact.getIdNumber() : "" %>" required>
        </div>
        <div class="col-md-6">
            <label class="form-label">Date of Birth</label>
            <input type="date" name="date_of_birth" class="form-control" value="<%= contact != null ? contact.getDateOfBirth() : "" %>" required>
        </div>
        <div class="col-md-6">
            <label class="form-label">Gender</label>
            <select name="gender" class="form-select" required>
                <option value="Male" <%= contact != null && "Male".equals(contact.getGender()) ? "selected" : "" %>>Male</option>
                <option value="Female" <%= contact != null && "Female".equals(contact.getGender()) ? "selected" : "" %>>Female</option>
            </select>
        </div>
        <div class="col-md-4">
            <label class="form-label">County</label>
            <input type="text" name="county" class="form-control" value="<%= contact != null ? contact.getCounty() : "" %>" required>
        </div>
        <div class="col-md-4 mb-4">
            <button type="submit" class="btn btn-primary-bg"><%= contact != null ? "Update" : "Save" %></button>
            <a href="contacts" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
