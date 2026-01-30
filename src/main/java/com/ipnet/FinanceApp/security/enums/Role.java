package com.ipnet.FinanceApp.security.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
	ADMIN(Set.of(
        Privilege.READ_PRIVILEGE,
        Privilege.WRITE_PRIVILEGE,
        Privilege.DELETE_PRIVILEGE,
        Privilege.UPDATE_PRIVILEGE
    )),
    USER(Set.of(
        Privilege.READ_PRIVILEGE
    ));
	
	@Getter
    private final Set<Privilege> privileges;

    Role(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = privileges
                .stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}