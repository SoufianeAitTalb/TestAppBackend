package com.indatacore.test.controller;

import com.indatacore.test.dto.UserDTO;
import com.indatacore.test.entities.User;
import com.indatacore.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("**")
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAllClients(){
        return this.userService.listUsers();
    }
    @GetMapping("/dto")
    public List<UserDTO> getAllClientsDTO(){
        return this.userService.listUsersDTO();
    }

    @GetMapping("/{id}")
    public UserDTO getClientById(@PathVariable Long id){
        return this.userService.loadUserByUserId(id);
    }


    @PostMapping("/add-user")
    public ResponseEntity<Map<String, String>> addNewUser(@RequestBody UserDTO userDTO) {
    Map<String, String> response = new HashMap<>();

    if (this.userService.loadUserByEmail(userDTO.getEmail()) != null) {
        response.put("status", "error");
        response.put("message", "Email already exists");
        return ResponseEntity.badRequest().body(response);
    }

    if (this.userService.addNewUser(userDTO) != null) {
        response.put("status", "success");
        response.put("message", "user added successfully");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "An error occurred");
    return ResponseEntity.badRequest().body(response);
}








}
