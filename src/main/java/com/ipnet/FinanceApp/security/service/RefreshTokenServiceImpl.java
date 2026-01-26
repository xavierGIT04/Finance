package com.ipnet.FinanceApp.security.service;

import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ipnet.FinanceApp.security.Repository.RefreshTokenRepository;
import com.ipnet.FinanceApp.security.Repository.UserRepository;
import com.ipnet.FinanceApp.security.dto.request.RefreshTokenRequest;
import com.ipnet.FinanceApp.security.dto.response.RefreshTokenResponse;
import com.ipnet.FinanceApp.security.entity.RefreshToken;
import com.ipnet.FinanceApp.security.entity.User;
import com.ipnet.FinanceApp.security.enums.TokenType;
import com.ipnet.FinanceApp.security.exception.TokenException;

import lombok.extern.slf4j.Slf4j;


@Service

@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService{
	
	private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Value("${jwt.refresh-token.expiration}")
    private Long refreshExpiration;
    
   
	public RefreshTokenServiceImpl(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository,
			JwtService jwtService) {
		super();
		this.userRepository = userRepository;
		this.refreshTokenRepository = refreshTokenRepository;
		this.jwtService = jwtService;
		
	}

	@Override
    public RefreshToken createRefreshToken(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        RefreshToken refreshToken =new  RefreshToken(
               user,
               Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()),
               Instant.now().plusMillis(refreshExpiration),
               false);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token == null){
           
            throw new TokenException(null, "Token is null");
        }
        if(token.getExpiryDate().compareTo(Instant.now()) < 0 ){
            refreshTokenRepository.delete(token);
            throw new TokenException(token.getToken(), "Refresh token was expired. Please make a new authentication request");
        }
        return token;
    }
    
    @Override
    public RefreshTokenResponse generateNewToken(RefreshTokenRequest request) {
        User user = refreshTokenRepository.findByToken(request.getRefreshToken())
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .orElseThrow(() -> new TokenException(request.getRefreshToken(),"Refresh token does not exist"));

        String token = jwtService.generateToken(user);
        return new RefreshTokenResponse(
                token,
                request.getRefreshToken(),
                TokenType.BEARER.name());
                
    }
}
