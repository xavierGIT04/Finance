package com.ipnet.FinanceApp.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipnet.FinanceApp.Entity.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	public Optional<Compte> findByPublicId(UUID publicId);
}
