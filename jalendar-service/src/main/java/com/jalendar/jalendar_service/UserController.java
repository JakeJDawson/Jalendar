package com.jalendar.jalendar_service;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        user.setPassword(dto.getPassword());
        
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserDTO dto) {
        User user = userRepo.findByEmail(dto.getEmail());

        if(user == null) {
            throw new RuntimeException("User not found!");
        }

        if(!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Password is incorrect!");
        }

        return user;
    }
}
