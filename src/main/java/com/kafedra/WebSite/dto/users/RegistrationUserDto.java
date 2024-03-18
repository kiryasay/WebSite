package com.kafedra.WebSite.dto.users;

import com.kafedra.WebSite.entities.users.Role;
import lombok.Data;

import java.util.Collection;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private Collection<Role> roles;
}
