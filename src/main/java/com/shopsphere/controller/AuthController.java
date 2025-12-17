package com.shopsphere.controller;

import com.shopsphere.config.JwtUtil;
import com.shopsphere.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(token);
        } else {
            log.error("Ivalid Username or passwords");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

@Data
class AuthRequest {
    private String username;
    private String password;
}

//@Data
//@AllArgsConstructor
//class AuthResponse {
//    private String token;
//}

