package com.ipnet.FinanceApp.security.service;

import com.ipnet.FinanceApp.security.dto.request.RefreshTokenRequest;
import com.ipnet.FinanceApp.security.dto.response.RefreshTokenResponse;
import com.ipnet.FinanceApp.security.entity.RefreshToken;

public interface RefreshTokenService {
	RefreshToken createRefreshToken(Long userId);
	RefreshToken verifyExpiration(RefreshToken token);
	RefreshTokenResponse generateNewToken(RefreshTokenRequest request);
}
