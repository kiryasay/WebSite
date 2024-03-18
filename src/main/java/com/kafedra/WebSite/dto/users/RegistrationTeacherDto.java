package com.kafedra.WebSite.dto.users;

import com.kafedra.WebSite.entities.users.Role;
import lombok.Data;

import java.util.Collection;

@Data
public class RegistrationTeacherDto {

    private String username;

    private String password;

    private String name;

    private String phone;

    private String mail;

    private String telegram;

    private String vkontakte;

    private String description;

    private Collection<Role> roles;
}