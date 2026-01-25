package com.ipnet.FinanceApp.DTO.Response;

import java.util.UUID;

public record ClientDtoResponse(
	    UUID publicId,
	    String nom,
	    String prenom,
	    String adresse,
	    String telephone
	) 
{}
