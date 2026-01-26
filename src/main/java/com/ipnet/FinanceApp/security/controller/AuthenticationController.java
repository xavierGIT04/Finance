package com.ipnet.FinanceApp.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipnet.FinanceApp.security.dto.request.AuthenticationRequest;
import com.ipnet.FinanceApp.security.dto.request.RefreshTokenRequest;
import com.ipnet.FinanceApp.security.dto.request.RegisterRequest;
import com.ipnet.FinanceApp.security.dto.response.AuthenticationResponse;
import com.ipnet.FinanceApp.security.dto.response.RefreshTokenResponse;
import com.ipnet.FinanceApp.security.service.AuthenticationService;
import com.ipnet.FinanceApp.security.service.RefreshTokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	@Autowired
    private  AuthenticationService authenticationService;
	
	@Autowired
    private  RefreshTokenService refreshTokenService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    
    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(refreshTokenService.generateNewToken(request));
    }



}
