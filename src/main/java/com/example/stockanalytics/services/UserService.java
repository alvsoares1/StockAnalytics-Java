package com.example.stockanalytics.services;

import com.example.stockanalytics.Repositories.UserRepository;
import com.example.stockanalytics.dtos.LoginRequestDTO;
import com.example.stockanalytics.dtos.ResponseDTO;
import com.example.stockanalytics.dtos.UserCreateDTO;
import com.example.stockanalytics.entities.User;
import com.example.stockanalytics.infra.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    public ResponseEntity<UserCreateDTO> createUser(UserCreateDTO data){
        Optional<User> user = this.userRepository.findByusername(data.username());

        if(user.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        User newUser = new User();
        newUser.setUsername(data.username());
        newUser.setPassword(passwordEncoder.encode(data.password()));
        this.userRepository.save(newUser);
        return ResponseEntity.ok(data);
    }

    public ResponseEntity<ResponseDTO> loginUser(LoginRequestDTO data){
        User user = this.userRepository.findByusername(data.username()).orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(data.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
