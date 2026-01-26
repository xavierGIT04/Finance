package com.ipnet.FinanceApp.security.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
	
	 	ADMIN,
	    USER;
	
		@Getter
	    private final Set<Privilege> privileges = null;

	    public List<SimpleGrantedAuthority> getAuthorities(){
	        List<SimpleGrantedAuthority> authorities = privileges
	                .stream()
	                .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
	                .collect(Collectors.toList());
	        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
	        return authorities;
	    }

}
