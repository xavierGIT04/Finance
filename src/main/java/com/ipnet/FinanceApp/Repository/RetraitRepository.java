package com.ipnet.FinanceApp.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipnet.FinanceApp.Entity.Retrait;

public interface RetraitRepository  extends JpaRepository<Retrait, Long>{
	public Optional<Retrait> findByPublicId(UUID publicId);

}
