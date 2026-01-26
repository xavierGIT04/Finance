package com.ipnet.FinanceApp.security.exception;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ErrorResponse {
    private int status;
    private String error;
    private Instant timestamp;
    private String message;
    private String path;
	public ErrorResponse(int status, String error, Instant timestamp, String message, String path) {
		super();
		this.status = status;
		this.error = error;
		this.timestamp = timestamp;
		this.message = message;
		this.path = path;
	}
    
    
}