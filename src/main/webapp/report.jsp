<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.contactregistry.ContactRegistryApp.model.Contact" %>

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
    
    <link rel="stylesheet" href="css/styles.css"/>
</head>
<body class="bg-light">
<jsp:include page="navbar.jsp" />
<div class="container my-5">
    <div class="card shadow-sm mb-4 no-print">
        <div class="card-body">
            <h3 class="card-title mb-4">Contact Report</h3>
            <form method="get" action="report" class="row gy-3 gx-4 align-items-end">
                <div class="col-md-4">
                    <label for="county" class="form-label">Filter by County</label>
                    <select name="county" id="county" class="form-select" >
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
                </div>

                <div class="col-md-3">
                    <label for="format" class="form-label">Export Format</label>
                    <select name="format" id="format" class="form-select">
                        <option value="pdf" selected>PDF</option>
                        <option value="csv">CSV</option>
                        <option value="xls">Excel</option>
                    </select>
                </div>

                <div class="col-md-2 d-grid">
                    <button type="submit" class="btn btn-success">Generate Report</button>
                </div>

                <div class="col-md-2 d-grid">
                    <button type="button" onclick="window.print()" class="btn btn-secondary">Print</button>
                </div>
            </form>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-striped bg-white shadow-sm">
            <thead class="table-light">
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
                    if (contactList != null && !contactList.isEmpty()) {
                        for (Contact contact : contactList) {
                %>
                <tr>
                    <td><%= contact.getFirstName() %> <%= contact.getLastName() %></td>
                    <td><%= contact.getIdNumber() %></td>
                    <td><%= contact.getPhoneNumber() %></td>
                    <td><%= contact.getEmailAddress() %></td>
                    <td><%= contact.getDateOfBirth() %></td>
                    <td><%= contact.getGender() %></td>
                    <td><%= contact.getCounty() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7" class="text-center">No contacts found for the selected county.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
