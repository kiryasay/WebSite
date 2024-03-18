package com.kafedra.WebSite.dto.users;

import com.kafedra.WebSite.entities.users.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class TeacherDto {

    private String name;

    private String phone;

    private String mail;

    private String telegram;

    private String vkontakte;

    private String description;

    private Collection<Role> roles;
}
