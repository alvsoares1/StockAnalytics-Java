package com.example.stockanalytics.controllers;

import com.example.stockanalytics.dtos.LoginRequestDTO;
import com.example.stockanalytics.dtos.ResponseDTO;
import com.example.stockanalytics.dtos.UserCreateDTO;
import com.example.stockanalytics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserCreateDTO> registerUser(@RequestBody UserCreateDTO body) {
        return this.userService.createUser(body);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginRequestDTO body) {
        return this.userService.loginUser(body);
    }
}
