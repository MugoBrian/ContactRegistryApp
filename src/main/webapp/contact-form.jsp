<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css"
</head>
<body class="bg-light">
<jsp:include page="navbar.jsp" />
<div class="container mt-4">
    <h2>${contact != null ? "Edit Contact" : "Add New Contact"}</h2>
    <form action="${contact != null ? 'contacts?update&id=' + contact.contact_id : 'contacts'}" method="${contact != null ? 'put' : 'post'}" class="row g-3">
        <c:if test="${contact != null}">
            <input type="hidden" name="contact_id" value="${contact.contact_id}">
        </c:if>
        <div class="row">
            <div class="col-md-6 col-lg-6">
                <label class="form-label">First Name</label>
                <input type="text" name="first_name" class="form-control" value="${contact.first_name}" required>
            </div>
            <div class="col-md-6 col-lg-6">
                <label class="form-label">Last Name</label>
                <input type="text" name="last_name" class="form-control" value="${contact.last_name}" required>
            </div>
        </div>
        
        <div class="col-md-6 col-lg-12">
            <label class="form-label">Phone Number</label>
            <input type="text" name="phone_number" minlength="10" class="form-control" value="${contact.phone_number}" required>
        </div>
        <div class="col-md-6 col-lg-12">
            <label class="form-label">Email Address</label>
            <input type="email" name="email_address" class="form-control" value="${contact.email_address}" required>
        </div>
        <div class="col-md-6 col-lg-12">
            <label class="form-label">ID Number</label>
            <input type="text" name="id_number" class="form-control" value="${contact.id_number}" required>
        </div>
        <div class="col-md-6">
            <label class="form-label">Date of Birth</label>
            <input type="date" name="date_of_birth" class="form-control" value="${contact.date_of_birth}" required>
        </div>
        <div class="col-md-6">
            <label class="form-label">Gender</label>
            <select name="gender" class="form-select" required>
                <option value="Male" ${contact.gender == 'Male' ? 'selected' : ''}>Male</option>
                <option value="Female" ${contact.gender == 'Female' ? 'selected' : ''}>Female</option>
            </select>
        </div>
        <div class="col-md-4">
            <label class="form-label">County</label>
            <input type="text" name="county" class="form-control" value="${contact.county}" required>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">${contact != null ? "Update" : "Save"}</button>
            <a href="contacts" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
