CREATE DATABASE IF NOT EXISTS contact_registry;
USE contact_registry;

CREATE TABLE users(
    username VARCHAR(100) UNIQUE NOT NULL,
    email_address VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users(username, email_address, password) VALUES ('admin', 'admin@example.com', MD5('admin123'));

-- -- FIELDS MUST MATCH
-- - Full Names (Will be the aggregate of first name and last name for simplicity)
-- - Phone Number  (Can be aggregate of country code and the 10 digit number)
-- - Email Address
-- - ID Number
-- - Date of Birth
-- - Gender
-- - County of Residence

CREATE TABLE IF NOT EXISTS contacts (
    first_name VARCHAR(100), 
    last_name VARCHAR(100),
    phone_number VARCHAR(20),
    email_address VARCHAR(100),
    id_number INT(15),
    date_of_birth DATE,
    gender ENUM('Male', 'Female', 'Other'),
    county VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO contacts (first_name, last_name, phone_number, email_address, id_number, date_of_birth, gender, county)
VALUES
('John', 'Doe', '+254712345678', 'john@example.com', '12345678', '1990-01-01', 'Male', 'Nairobi'),
('Jane', 'Smith', '+254723456789', 'jane@example.com', '87654321', '2005-05-05', 'Female', 'Mombasa');