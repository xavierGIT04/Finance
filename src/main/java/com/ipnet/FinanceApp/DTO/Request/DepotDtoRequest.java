package com.ipnet.FinanceApp.DTO.Request;

import java.util.UUID;

public record DepotDtoRequest(
		String nomDeposant,
		String telephoneDeposant,
		String adresseDeposant,
		float montant,
		UUID compte	
		) {

}
