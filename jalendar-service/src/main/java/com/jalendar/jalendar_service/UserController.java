package com.jalendar.jalendar_service;

// Import statements.
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
// All endpoints here are prefixed with /api/users
@RequestMapping("/api/users")
public class UserController {
    // User database access.
    private final UserRepository userRepo;
    // Allows password encryption and verification.
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    // Allows JWT token generation.
    private final JwtUtil jwtUtil;

    // Constructor injection. Basically makes Spring do stuff automatically.
    public UserController(UserRepository userRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    // Manages registration.
    @PostMapping("/register")
    public User register(@RequestBody UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        /**
         * Worth noting that I believe this to be a security flaw.
         */
        return userRepo.save(user);
    }

    // Manages login.
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        //Check the encoded password.
        if(!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Password is incorrect!");
        }

        // Generate JWT token.
        String token = jwtUtil.generateToken(user.getEmail(), user.getId());

        // Return a relevant token.
        return ResponseEntity.ok(
            new UserLoginResponse(token)
        );
    }
}
