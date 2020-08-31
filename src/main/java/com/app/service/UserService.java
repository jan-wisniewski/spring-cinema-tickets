package com.app.service;

import com.app.dto.CreateUserDto;
import com.app.enums.Role;
import com.app.exception.UserServiceException;
import com.app.mappers.Mapper;
import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.validator.CreateUserDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findById (Integer userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserServiceException("FAILED"));
    }

    public User findByUsername (String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UserServiceException("FAILED"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Integer createUser(CreateUserDto userDto) {
        if (Objects.isNull(userDto)){
            throw new UserServiceException("User Dto is null");
        }
        var userDtoValidator = new CreateUserDtoValidator();
        var errors = userDtoValidator.validate(userDto);
        if (!errors.isEmpty()) {
            var errorsMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + " : " + e.getValue())
                    .collect(Collectors.joining("\n"));
            throw new UserServiceException("create user validation errors: " + errorsMessage);
        }
        var user = Mapper.fromCreateUserDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new UserServiceException("user with this username already exists");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserServiceException("user with this email already exists");
        }
        var addedUser = userRepository
                .add(user)
                .orElseThrow(() -> new UserServiceException("cannot insert user to db"));
        return addedUser.getId();
    }

    public User edit(User chosenUser) {
        return userRepository.update(chosenUser).orElseThrow(() -> new UserServiceException("Cannot edit user"));
    }

    public boolean delete(Integer id) {
        return userRepository.deleteById(id);
    }
}
