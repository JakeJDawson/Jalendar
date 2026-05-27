package com.jalendar.jalendar_service;

public class UserLoginResponse {
    private Long id;
    private String email;

    public UserLoginResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
}
