package com.kafedra.WebSite.dto.users;

import com.kafedra.WebSite.entities.users.Role;
import lombok.Data;

import java.util.Collection;

@Data
public class RegistrationStudentDto {
    private String username;
    private String password;
    private String name;
    private String group;
    private String course;
    private Collection<Role> roles;

}