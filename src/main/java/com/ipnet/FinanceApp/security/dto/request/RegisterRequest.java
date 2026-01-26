package com.ipnet.FinanceApp.security.dto.request;

import com.ipnet.FinanceApp.security.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	
	 	@NotBlank(message = "firstname is required")
	    private String firstname;
	 	
	    @NotBlank(message = "lastname is required")
	    private String lastname;
	    
	    @NotBlank(message = "username is required")
	    private String username;
	    
	    @NotBlank(message = "password is required")
	    
	    private String password;
	    @NotNull
	    private Role role;
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
	    
	    
}

