package com.ipnet.FinanceApp.DTO.Response;

import java.util.UUID;

public record VirementDtoResponse(
		UUID publicId,
		float montant,
		String compteSource,
		String compteDestination,
		String client
		) {

}
