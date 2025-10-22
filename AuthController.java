package com.example.Resume_system.controller;

import com.example.Resume_system.Entity.Role;
import com.example.Resume_system.dto.LoginRequest;
import com.example.Resume_system.dto.LoginResponse;
import com.example.Resume_system.Entity.User;
import com.example.Resume_system.Repository.RoleRepository;
import com.example.Resume_system.Repository.UserRepository;
import com.example.Resume_system.model.RoleName;
import com.example.Resume_system.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        String jwt = jwtUtil.generateJwtToken(auth);
        return ResponseEntity.ok(new LoginResponse(jwt, "Bearer", 86400000L));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already used");
        }

        User u = new User();
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setFirstName("");
        u.setLastName("");

        // ✅ Get role entity from DB (must already exist in DB)
        Role role = roleRepository.findByName(RoleName.ROLE_USER)
                        .orElseThrow(()-> new RuntimeException("Role not found"));
        u.setRoles(Set.of());

        userRepository.save(u);
        return ResponseEntity.ok("User registered successfully");


    }
}


