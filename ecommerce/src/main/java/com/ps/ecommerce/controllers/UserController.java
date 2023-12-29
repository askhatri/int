package com.ps.ecommerce.controllers;

import com.ps.ecommerce.entities.User;
import com.ps.ecommerce.misc.AuthenticationManager;
import com.ps.ecommerce.misc.AuthenticationResponse;
import com.ps.ecommerce.misc.LoginRequest;
import com.ps.ecommerce.misc.UsernamePasswordAuthenticationToken;
import com.ps.ecommerce.repositories.UserRepository;
import com.ps.ecommerce.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpService otpService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Check for existing user with same username
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(new User(null, "Username already exists"));
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());
        authenticationManager.authenticate(authenticationToken);

        String otp = otpService.generateOtp(loginRequest.getUsername());
        otpService.sendOtp(loginRequest.getUsername(), otp);

        return ResponseEntity.ok(new AuthenticationResponse("OTP sent successfully", otp));
    }
}