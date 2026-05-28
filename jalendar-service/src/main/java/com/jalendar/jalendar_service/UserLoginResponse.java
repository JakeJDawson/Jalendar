package com.jalendar.jalendar_service;

/**
 * This file is super basic because it's kind of a bare bones object. The way
 * Springboot, JWT, and React work together makes this just the best way to
 * handle certain things. At least, the best way I know of.
 */
public class UserLoginResponse {
    private String token;

    public UserLoginResponse(String token) {this.token = token;}

    public String getToken() {return token;}
    public void setToken(String token) {this.token = token;}
}
