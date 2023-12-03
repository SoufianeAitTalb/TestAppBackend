package com.indatacore.test.services.impl;


import com.indatacore.test.dto.UserDTO;
import com.indatacore.test.entities.User;
import com.indatacore.test.services.UserService;

import com.indatacore.test.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImp(UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User addNewUser(UserDTO userDto) {
        User user = userDTOToUser(userDto);
        return this.userRepo.save(user);
    }

    @Override
    public User updateUser(Long id, UserDTO userDto) {
        return null;
    }


    public User userDTOToUser( UserDTO userDto) {
        if(userDto == null){
            return null;
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return user;

    }

    public UserDTO userToUserDTO(User user) {
        if(user == null){
            return null;
        }
        UserDTO userDto = new UserDTO();

        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;


    }


    @Override
    public List<UserDTO> listUsersDTO() {
        List<User> users = this.userRepo.findAll();
        return users.stream()
                .map(this::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> listUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public User loadUserByEmail(String email) {
        return this.userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public User loadUserById(Long id) {
        return this.userRepo.findById(id).orElse(null);
    }

    @Override
    public UserDTO loadUserByUserId(Long id) {
        User user = this.userRepo.findById(id).orElse(null);
        return userToUserDTO(user);
    }
}
