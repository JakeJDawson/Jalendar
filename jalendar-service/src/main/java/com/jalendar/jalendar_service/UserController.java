package com.jalendar.jalendar_service;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail());

        if(user == null) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("User not found!");
        }

        if(!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Password is incorrect!");
        }

        return ResponseEntity.ok(
            new UserLoginResponse(user.getId(), user.getEmail())
        );
    }
}
