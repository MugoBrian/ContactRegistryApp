package com.contactregistry.ContactRegistryApp.model;

public class User {

    private int id;
    private String email_address;
    private String password;
    private String first_name;
    private String last_name;

    public User(int id, String email_address, String password, String first_name, String last_name) {
        this.id = id;
        this.email_address = email_address;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFullName() {
        return this.first_name + " " + this.last_name;
    }

}
