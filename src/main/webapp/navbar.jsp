<%@ page session="true" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<nav class="navbar navbar-expand-sm navbar-dark shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-white" href="dashboard">Contact Registry</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link text-white" href="contacts?action=new">Add Contact</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="contacts">All Contacts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="report">Reports</a>
                </li>
            </ul>
            <div class="d-flex">
                <a class="btn btn-light btn-sm" href="logout">Logout</a>
            </div>
        </div>
    </div>
</nav>
