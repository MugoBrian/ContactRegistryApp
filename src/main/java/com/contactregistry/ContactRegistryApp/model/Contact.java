package com.contactregistry.ContactRegistryApp.model;

import java.time.LocalDate;

// full_name  
// phone_number 
// email_address
// id_number 
// date_of_birth
// gender     
// county 

public class Contact {
    private int id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email_address;
    private int id_number;
    private LocalDate date_of_birth;
    private String gender;
    private String county;

    public Contact() {
    }

    public Contact(int id, String first_name, String last_name, String phone_number, String email_address,
            int id_number,
            LocalDate date_of_birth, String gender, String county) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.id_number = id_number;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.county = county;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // First Name, Last Name and Full Name

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getFullName() {
        return this.first_name + " " + this.last_name;
    }

    public String getPhoneNumber() {
        return this.phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmailAddress() {
        return this.email_address;
    }

    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }

    public int getIdNumber() {
        return this.id_number;
    }

    public void setIdNumber(int id_number) {
        this.id_number = id_number;
    }

    public LocalDate getDateOfBirth() {
        return this.date_of_birth;
    }

    public void setDateOfBirth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCounty() {
        return this.county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", full_name='" + getFullName() + "'" +
                ", phone_number='" + getPhoneNumber() + "'" +
                ", email_address='" + getEmailAddress() + "'" +
                ", id_number='" + getIdNumber() + "'" +
                ", date_of_birth='" + getDateOfBirth() + "'" +
                ", gender='" + getGender() + "'" +
                ", county='" + getCounty() + "'" +
                "}";
    }

}
