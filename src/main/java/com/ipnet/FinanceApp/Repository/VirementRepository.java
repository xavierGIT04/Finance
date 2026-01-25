package com.ipnet.FinanceApp.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipnet.FinanceApp.Entity.Virement;

public interface VirementRepository extends JpaRepository<Virement, Long>{
	public Optional<Virement> findByPublicId(UUID publicId);

}
