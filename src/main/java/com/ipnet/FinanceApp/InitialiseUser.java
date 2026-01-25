package com.ipnet.FinanceApp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ipnet.FinanceApp.security.model.User;
import com.ipnet.FinanceApp.security.repository.UserRepository;

@Component
public class InitialiseUser implements CommandLineRunner{

	@Autowired private UserRepository userRepository;
	@Autowired private PasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		if(userRepository.count() == 0) {
			User admin = new User();
			admin.setNom("GBANDI Waké");
			admin.setUsername("admin");
			admin.setPassword(encoder.encode("Laisser@admin"));
			admin.setRoles("ADMIN");
			admin.setCreateDate(LocalDateTime.now());
			userRepository.save(admin);
		}
	}

}
