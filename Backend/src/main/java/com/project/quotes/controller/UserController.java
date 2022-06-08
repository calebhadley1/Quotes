package com.project.quotes.controller;

import com.project.quotes.model.User;
import com.project.quotes.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//
//    @PutMapping
//    public ResponseEntity updateUser(@RequestBody User user) {
//        userService.updateUser(user);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDetails> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteUser(@PathVariable String id) {
//        userService.deleteUser(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
}
