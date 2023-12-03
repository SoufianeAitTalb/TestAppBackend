package com.indatacore.test.services.impl;

import com.indatacore.test.dto.RoleDTO;
import com.indatacore.test.dto.UserDTO;
import com.indatacore.test.entities.Role;
import com.indatacore.test.entities.User;
import com.indatacore.test.services.AccountService;
import com.indatacore.test.repositories.RoleRepo;
import com.indatacore.test.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AccountServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User addNewUser(UserDTO userDto) {
        User user = this.userRepo.findByEmail(userDto.getEmail()).orElse(null);
        if(user!=null) throw new RuntimeException("Email already exists");
        user = userDTOToUser(userDto);
        return this.userRepo.save(user);
    }

    @Override
    public Role addNewRole(RoleDTO roleDTO) {
        Role role = roleRepo.findById(roleDTO.getRole()).orElse(null);
        if(role!=null) throw new RuntimeException("Role already exists");
        role = Role.builder().role(roleDTO.getRole()).build();
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = userRepo.findByEmail(email).orElse(null);
        if(user==null) throw  new RuntimeException("User not exists");
        Role role = roleRepo.findById(roleName).orElse(null);
        if(role==null) throw  new RuntimeException("Role not exists");

        user.getRoles().add(role);

    }

    @Override
    public void removeRoleToUser(String email, String role) {

    }

    @Override
    public User loadUserByEmail(String email){
        return this.userRepo.findByEmail(email).orElse(null);
    }

    @Override
    public User loadUserByUserName(String userName) {
        return this.userRepo.findByUserName(userName).orElse(null);
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
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;


    }
}
