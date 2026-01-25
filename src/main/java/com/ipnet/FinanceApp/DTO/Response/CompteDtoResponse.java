package com.ipnet.FinanceApp.DTO.Response;

import java.util.UUID;

public record CompteDtoResponse(
		UUID publicId,
		String numero,
		float solde,
		String type,
		String proprietaire
		) {}
