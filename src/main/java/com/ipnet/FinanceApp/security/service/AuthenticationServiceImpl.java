package com.ipnet.FinanceApp.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipnet.FinanceApp.security.Repository.UserRepository;
import com.ipnet.FinanceApp.security.dto.request.AuthenticationRequest;
import com.ipnet.FinanceApp.security.dto.request.RegisterRequest;
import com.ipnet.FinanceApp.security.dto.response.AuthenticationResponse;
import com.ipnet.FinanceApp.security.entity.User;
import com.ipnet.FinanceApp.security.enums.TokenType;



@Service @Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    
    
    
    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder, JwtService jwtService,
			UserRepository userRepository, AuthenticationManager authenticationManager,
			RefreshTokenService refreshTokenService) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.refreshTokenService = refreshTokenService;
	}

	@Override
    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
                
        user = userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getId());

        var roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();

        return new AuthenticationResponse(
        		user.getId(),
        		user.getUsername(),
        		roles,
                jwt,
                refreshToken.getToken(),  
                TokenType.BEARER.name()
                );
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email 		or password."));
        var jwt = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(user.getId());
        var roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();
        
        return new AuthenticationResponse(
        		user.getId(),
        		user.getUsername(),
        		roles,
                jwt,
                refreshToken.getToken(),  
                TokenType.BEARER.name());
    }
}
