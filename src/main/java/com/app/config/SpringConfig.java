package com.app.config;

import com.app.enums.Role;
import com.app.model.User;
import com.app.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {

    public SpringConfig(UserRepository userRepository, PasswordEncoder passwordEncoder){
 /*     var user = User
                .builder()
                .email("user")
                .name("user")
                .surname("user")
                .username("user")
                .password(passwordEncoder.encode("user"))
                .role(Role.USER)
                .build();
        var user2 = User
                .builder()
                .email("admin")
                .name("admin")
                .surname("admin")
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .build();
        userRepository.add(user);
        userRepository.add(user2);*/
    }

}
