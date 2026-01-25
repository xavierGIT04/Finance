package com.ipnet.FinanceApp.DTO.Request;

import java.util.UUID;

public record CompteDtoRequest(
		String numero,
		float solde,
		UUID typeCompte,
		UUID proprietaire
		) {}
