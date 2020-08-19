package com.app.model.thymeleaf;

import com.app.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsersWithObj {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
}
