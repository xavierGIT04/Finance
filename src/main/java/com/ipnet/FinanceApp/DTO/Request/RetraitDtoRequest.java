package com.ipnet.FinanceApp.DTO.Request;

import java.util.UUID;

public record RetraitDtoRequest(
		float montant,
		UUID compte,
		UUID client
		) {}
