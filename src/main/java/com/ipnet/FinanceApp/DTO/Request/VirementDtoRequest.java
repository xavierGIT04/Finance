package com.ipnet.FinanceApp.DTO.Request;

import java.util.UUID;

public record VirementDtoRequest(
		float montant,
		UUID compteSource,
		UUID compteDestination,
		UUID client
		) {}
