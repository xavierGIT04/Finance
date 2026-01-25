package com.ipnet.FinanceApp.DTO.Response;

import java.util.UUID;

public record RetraitDtoResponse(
		UUID publicId,
		float montant,
		String compte,
		String client
		) {

}
