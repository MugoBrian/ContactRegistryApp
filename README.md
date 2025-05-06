# Contact Registry Web Application

A full-stack Java web application for managing contact information with admin dashboard and reporting capabilities.


## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Build & Run](#build&run)
- [Troubleshooting](#troubleshooting)


## Features

- **User Authentication**
  - Secure login/registration system
  - Role-based access control (Admin/User)

- **Contact Management**
  - CRUD operations for contacts
  - Stores: Full name, phone, email, ID number, DOB, gender, county

- **Admin Dashboard**
  - Visual statistics (gender/county distribution)
  - Recent contacts table

- **Reporting System**
  - Filterable by county
  - Printable format

- **Security**
  - MD5 password hashing
  - Session management
  - SQL injection prevention


## Technologies

- **Backend**: Java Servlets
- **Database**: MySQL 8.0+
- **Server**: Apache Tomcat 9+
- **Build Tool**: Maven
- **Frontend**:
  - Bootstrap 5
  - Chart.js
  - JSP, HTML & CSS
  - jQuery (Bootstrap)
- **Security**: MD5 hash password - not the best paractice really


## Prerequisites

- Java JDK 21+
- Apache Tomcat 9+
- MySQL 8.0+
- Maven 3.6+
- Eclipse / Netbeans IDE


## Installation

### 1. Clone Repository

```bash
git clone https://github.com/yourusername/contact-registry.git
cd contact-registry

```

### 2. Database Setup

```bash
mysql -u root -p < db/setup.sql
```

### 3 Database Credentials
Update src/main/java/com/contactregistry/util/DBUtil.java:

```
private static final String URL = "jdbc:mysql://localhost:3306/contact_registry";
private static final String USER = "your_db_username";
private static final String PASSWORD = "your_db_password";

```

### 4. Default Admin Credentials
Email Address: admin@example.com
Password: admin123


## Configuration

### Configure Apache Tomcat
1. Add Tomcat Server to NetBeans:
Go to Tools → Servers

Click Add Server.

Choose Apache Tomcat.

Set the installation path and credentials

2. Set the Tomcat server for your project:
Right-click the project → Properties.

Go to Run → Select the server and context path (e.g., /ContactRegistryApp).


## Build & Run

The best way to run this project use via Netbeans or Eclipse IDE, since it simplifies the process of building and running the application.

Right-click on the project.

Choose Clean and Build.

Then choose Run.

This will deploy the app to Tomcat and open it in your browser (e.g., http://localhost:8080/ContactRegistryApp).



## Troubleshooting
Common Issues:

1. Database Connection Failed:
- Verify MySQL service is running.
- Check credentials in `utils/DBUtil.java`.
- Ensure MySQL JDBC driver is in classpath.

2. Build Failures:

- Clean Maven cache: `mvn clean install -U`.

- Verify network connection for dependencies.

3. 403 Forbidden Errors:

- Ensure user is authenticated.
- Check session validity.
