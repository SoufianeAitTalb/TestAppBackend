package com.indatacore.test.services;



import com.indatacore.test.dto.UserDTO;
import com.indatacore.test.entities.User;

import java.util.List;

public interface UserService {

    User addNewUser(UserDTO userDto);
    User updateUser(Long id, UserDTO userDto);


    List<UserDTO> listUsersDTO();
    List<User> listUsers();
    User loadUserByEmail(String email);
    User loadUserById(Long id);
    UserDTO loadUserByUserId(Long id);


}
