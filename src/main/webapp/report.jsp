<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Contact Report</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        @media print {
            .no-print {
                display: none;
            }
        }
    </style>
</head>
<body class="container mt-5">

    <div class="no-print">
        <h2>Printable Contact Report</h2>

        <form method="get" action="report" class="mb-4">
            <label for="county">Filter by County:</label>
            <select name="county" id="county" class="form-select w-25 d-inline-block">
                <option value="">-- All Counties --</option>
                <%
                    List<String> counties = (List<String>) request.getAttribute("counties");
                    String selectedCounty = (String) request.getAttribute("selectedCounty");
                    for (String c : counties) {
                        String selected = (c.equals(selectedCounty)) ? "selected" : "";
                %>
                    <option value="<%= c %>" <%= selected %>><%= c %></option>
                <% } %>
            </select>
            <button type="submit" class="btn btn-primary">Filter</button>
            <button onclick="window.print()" type="button" class="btn btn-secondary">Print</button>
        </form>
    </div>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Full Name</th>
                <th>ID Number</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Date of Birth</th>
                <th>Gender</th>
                <th>County</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Contact> contactList = (List<Contact>) request.getAttribute("contacts");
                for (Contact contact : contactList) {
            %>
                <tr>
                    <td><%= contact.getFirstName() %></td>
                    <td><%= contact.getLastName() %></td>
                    <td><%= contact.getIdNumber() %></td>
                    <td><%= contact.getPhoneNumber() %></td>
                    <td><%= contact.getEmailAddress() %></td>
                    <td><%= contact.getDateOfBirth() %></td>
                    <td><%= contact.getGender() %></td>
                    <td><%= contact.getCounty() %></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>

</body>
</html>
