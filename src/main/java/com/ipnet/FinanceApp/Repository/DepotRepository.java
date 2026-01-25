package com.ipnet.FinanceApp.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipnet.FinanceApp.Entity.Depot;

public interface DepotRepository extends JpaRepository<Depot, Long>{
	public Optional<Depot> findByPublicId(UUID publicId);

}
