package com.app.dto;

import com.app.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EditProfileDto {
    private Integer id;
    private String name;
    private String surname;
    private Role role;
    private String username;
    private String email;
    private String password;
    private String newPassword;
}
