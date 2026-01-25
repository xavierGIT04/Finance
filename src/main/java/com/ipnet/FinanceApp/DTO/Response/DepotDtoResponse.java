package com.ipnet.FinanceApp.DTO.Response;

import java.util.UUID;

public record DepotDtoResponse(
		UUID publicId,
		String nomDeposant,
		String telephoneDeposant,
		String adresseDeposant,
		float montant,
		String compte	
		) {

}
