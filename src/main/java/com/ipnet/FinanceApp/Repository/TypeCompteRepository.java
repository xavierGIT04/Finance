package com.ipnet.FinanceApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipnet.FinanceApp.Entity.TypeCompte;
import java.util.UUID;


public interface TypeCompteRepository extends JpaRepository<TypeCompte, Long> {
	public Optional<TypeCompte> findByPublicId(UUID publicId);
}
