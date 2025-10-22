package com.example.Resume_system.service;

import com.example.Resume_system.Entity.User;
import com.example.Resume_system.Repository.UserRepository;
import com.example.Resume_system.dto.AuthRequest;
import com.example.Resume_system.dto.AuthResponse;
import com.example.Resume_system.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(String name, String email, String password){
        if (userRepository.existsByEmail(email)) throw new RuntimeException("Email already registered");
        User user = new User();
        user.setFirstName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        var token = jwtUtil.generateJwtToken(user.getEmail());
        return new AuthResponse(token);

    }

    public AuthResponse login(AuthRequest request){
        Optional<User> userOpt = userRepository.findByEmail(request.email());
        if (userOpt.isEmpty()) throw new RuntimeException(("Invalid credentials"));
        User user = userOpt.get();
        if (!passwordEncoder.matches(request.password(), user.getPassword())) throw new RuntimeException("Invalid credentials");

        var token = jwtUtil.generateJwtToken(user.getEmail());
        return new AuthResponse(token);
    }

}
