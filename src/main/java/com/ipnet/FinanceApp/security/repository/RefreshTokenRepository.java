package com.ipnet.FinanceApp.security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipnet.FinanceApp.security.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(String token);
}
