package com.app.validator;

import com.app.config.WebSecurityConfig;
import com.app.dto.CreateUserDto;
import com.app.validator.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class CreateUserDtoValidator  implements Validator<CreateUserDto> {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Map<String, String> validate(CreateUserDto item) {
        var errors = new HashMap<String, String>();
        if (!isNameStartsWithUppercase(item)) {
            errors.put("Name", "Should starts with uppercase");
        }
        if (!isSurnameStartsWithUppercase(item)) {
            errors.put("Surname", "Should starts with uppercase");
        }
        if (!isEmailValid(item)) {
            errors.put("Email", "E-mail is not valid");
        }
        if (!isMatchingPassword(item)){
            errors.put("Password", "Passwords are not the same");
        }
        return errors;
    }

    private boolean isNameStartsWithUppercase(CreateUserDto userDto) {
        return userDto.getName().matches("[A-Z][a-z]+");
    }

    private boolean isSurnameStartsWithUppercase(CreateUserDto userDto) {
        return userDto.getSurname().matches("[A-Z][a-z]+");
    }

    private boolean isEmailValid(CreateUserDto userDto) {
        return userDto.getEmail().matches("([A-Za-z0-9]\\.?)+@[A-Za-z0-9]+\\.[a-z]+");
    }

    private boolean isMatchingPassword (CreateUserDto userDto){
        System.out.println(userDto.getRepeatedPassword());
        System.out.println(userDto.getPassword());
        return userDto.getPassword().equals(userDto.getRepeatedPassword());
       // return userDto.getPassword().equals(userDto.getRepeatedPassword());
    }
}
