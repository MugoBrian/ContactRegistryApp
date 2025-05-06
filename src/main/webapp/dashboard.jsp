<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.*" %>
<%@ page import="java.util.Map" %>
<%@ page session="true" %>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css"/>
    <script type="text/javascript">
        google.charts.load('current', {packages: ['corechart']});

        google.charts.setOnLoadCallback(drawGenderChart);
        google.charts.setOnLoadCallback(drawCountyChart);

        function drawGenderChart() {
            var data = google.visualization.arrayToDataTable([
                ['Gender', 'Count'],

                <% 
                Map<String, Integer> genderStats = (Map<String, Integer>) request.getAttribute("genderStats");

                if (genderStats != null) {
                    for (Map.Entry<String, Integer> entry : genderStats.entrySet()) {
                %>
                        ['<%= entry.getKey() %>', <%= entry.getValue() %>],
                <%
                    }
                } else { %>
                    ['No Data', 0],

                <% } %>
            ]);

            var options = {
                title: 'Contacts by Gender',
                colors: ['#136958']
            };

            var chart = new google.visualization.PieChart(document.getElementById('genderChart'));
            chart.draw(data, options);
        }

        function drawCountyChart() {
            var data = google.visualization.arrayToDataTable([
                ['County', 'Count'],

                <% 
                Map<String, Integer> countyStats = (Map<String, Integer>) request.getAttribute("countyStats");

                if(countyStats != null){
                    for (Map.Entry<String, Integer> entry : countyStats.entrySet()) {
                %>
                    ['<%= entry.getKey() %>', <%= entry.getValue() %>],
                <%
                    }
                } else { %>
                    ['No Data', 0],

                <% } %>
                
            ]);

            var options = {
                title: 'Contacts by County',
                hAxis: { title: 'County' },
                vAxis: { title: 'Number of Contacts' },
                legend: 'none',
                colors: ['#136958']
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('countyChart'));
            chart.draw(data, options);
        }
    </script>
</head>

<body class="bg-light">
    <jsp:include page="navbar.jsp" />
    
    <div class="container dashboard-container mt-4">
        <div class="row g-4">
            <!-- Charts -->
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Contacts by Gender</h5>
                        <div id="genderChart" style="height: 300px;"></div>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">Contacts by County</h5>
                        <div id="countyChart" style="height: 300px;"></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Recent Contacts Table -->
        <div class="card shadow-sm mt-5">
            <div class="card-header bg-primary-bg text-white">
                Recently Added Contacts
            </div>
            <div class="card-body table-responsive shadow rounded">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-light">
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Created</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- Loop through recentContacts --%>
                        <% 
                            List<Map<String, String>> recent = (List<Map<String, String>>) request.getAttribute("recentContacts");
                            if (recent != null && !recent.isEmpty()) {
                                for (Map<String, String> contact : recent) {
                        %>
                        <tr>
                            <td><%= contact.get("first_name") %></td>
                            <td><%= contact.get("last_name") %></td>
                            <td><%= contact.get("email_address") %></td>
                            <td><%= contact.get("phone_number") %></td>
                            <td><%= contact.get("created_at") %></td>
                        </tr>
                        <%      }
                            } else {
                        %>
                        <tr>
                            <td colspan="5" class="text-center">No recent contacts found.</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
