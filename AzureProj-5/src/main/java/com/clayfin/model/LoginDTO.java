package com.clayfin.model;

import lombok.Data;

@Data
public class LoginDTO {
    private String usernameOrEmail;
    private String password;  
    
}