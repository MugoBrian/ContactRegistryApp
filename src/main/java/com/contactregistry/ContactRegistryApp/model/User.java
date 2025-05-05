package com.contactregistry.ContactRegistryApp.model;

public class User {

    private String username;
    private String email_address;

    // -- username
    // -- email_address
    // -- password

    public User(String username, String email_address) {
        this.username = username;
        this.email_address = email_address;

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return this.email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

}
