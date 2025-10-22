package com.example.Resume_system.security;

import com.example.Resume_system.Entity.User;
import com.example.Resume_system.Repository.UserRepository;
import org.springframework.stereotype.Service;

//public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

//import com.example.resume.entity.User;
//import com.example.Resume_system.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

    @Service
    public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
        private final UserRepository userRepository;
        public CustomUserDetailsService(UserRepository repo) { this.userRepository = repo; }

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
            var authorities = user.getRoles().stream()
                    .map(r -> new SimpleGrantedAuthority(r.getName().name()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }
}
