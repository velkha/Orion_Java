package org.orion.assistant.persistence.service.impl;

import org.orion.assistant.enums.Role;
import org.orion.assistant.persistence.dao.auth.AuthResponse;
import org.orion.assistant.persistence.dao.auth.SignInReq;
import org.orion.assistant.persistence.dao.auth.SignUpReq;
import org.orion.assistant.persistence.model.User;
import org.orion.assistant.persistence.repositories.UserRepository;
import org.orion.assistant.persistence.service.AuthService;
import org.orion.assistant.persistence.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private  UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;
    private  JwtService jwtService;
    private  AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse signUp(SignUpReq request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwt).build();
    }

    @Override
    public AuthResponse signIn(SignInReq request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByEmail(request.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        String jwt = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwt).build();
    }
}
