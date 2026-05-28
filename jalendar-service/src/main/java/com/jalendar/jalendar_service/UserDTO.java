package com.jalendar.jalendar_service;

/**
 * Basically, this is a DTO or Data Transfer Object. It serves as a middle man
 * between the front end and the back end, and when done well, would protect
 * sensitive data. I'm 100% certain I'm not doing it super well, but I AM
 * trying.
 */
public class UserDTO {
    // Basic info to be held.
    private String email;
    private String password;

    // Basic default constructor.
    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters.
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters.
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) {this.password = password; }
}
