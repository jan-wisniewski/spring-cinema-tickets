package com.app.model;

import com.app.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private Role role;
}
