package com.indatacore.test.services;



import com.indatacore.test.dto.RoleDTO;
import com.indatacore.test.dto.UserDTO;
import com.indatacore.test.entities.Role;
import com.indatacore.test.entities.User;

public interface AccountService {

    User addNewUser(UserDTO userDto);
    Role addNewRole(RoleDTO roleDTO);

    void addRoleToUser(String email , String role);
    void removeRoleToUser(String email , String role);

    User loadUserByEmail(String email);
    User loadUserByUserName(String userName);


}
