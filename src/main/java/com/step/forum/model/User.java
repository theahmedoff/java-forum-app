package com.step.forum.model;

import lombok.Data;

@Data
public class User {

    private int id;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String token;
    private String imagePath;
    private int status;
    private Role role;

}
