package com.ipnet.FinanceApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipnet.FinanceApp.Entity.Client;
import java.util.Optional;
import java.util.UUID;


public interface ClientRepository extends JpaRepository<Client, Long> {
	public Optional<Client> findByPublicId(UUID publicId);
}
