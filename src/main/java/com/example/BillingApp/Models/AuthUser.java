package com.example.BillingApp.Models;

import lombok.Data;

@Data
public class AuthUser {
    private String userId;
    private String role;

    public AuthUser(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }
}

