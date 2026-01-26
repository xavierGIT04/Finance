package com.ipnet.FinanceApp.security.service;

import com.ipnet.FinanceApp.security.dto.request.AuthenticationRequest;
import com.ipnet.FinanceApp.security.dto.request.RegisterRequest;
import com.ipnet.FinanceApp.security.dto.response.AuthenticationResponse;

public interface AuthenticationService {
	AuthenticationResponse register(RegisterRequest request);
	AuthenticationResponse authenticate(AuthenticationRequest request);
}
