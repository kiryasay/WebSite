package com.kafedra.WebSite.dto.users;

import com.kafedra.WebSite.entities.users.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Collection<Role> roles;

}
