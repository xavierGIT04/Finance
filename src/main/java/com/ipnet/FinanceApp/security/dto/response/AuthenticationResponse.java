package com.ipnet.FinanceApp.security.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	
		private  Long id;
	    private String username;
	    private List<String> roles;

	    @JsonProperty("access_token")
	    private String accessToken;
	    
	    @JsonProperty("refresh_token")
	    private String refreshToken;
	    
	    @JsonProperty("token_type")
	    private String tokenType;

		public AuthenticationResponse(Long id, String username, List<String> roles, String accessToken,
				String refreshToken, String tokenType) {
			super();
			this.id = id;
			this.username = username;
			this.roles = roles;
			this.accessToken = accessToken;
			this.refreshToken = refreshToken;
			this.tokenType = tokenType;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public List<String> getRoles() {
			return roles;
		}

		public void setRoles(List<String> roles) {
			this.roles = roles;
		}

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getRefreshToken() {
			return refreshToken;
		}

		public void setRefreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
		}

		public String getTokenType() {
			return tokenType;
		}

		public void setTokenType(String tokenType) {
			this.tokenType = tokenType;
		}
	    
		
	    
	    
}
