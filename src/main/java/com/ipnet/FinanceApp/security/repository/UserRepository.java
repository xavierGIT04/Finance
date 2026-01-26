package com.ipnet.FinanceApp.security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipnet.FinanceApp.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

}
