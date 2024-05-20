package com.wdev.secutity.dtos;

import com.wdev.secutity.entities.User;

import java.util.Optional;

public class UserDTO {
    private String username;
    private String roles;

    public UserDTO() {
    }

    public UserDTO(String username, String roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
