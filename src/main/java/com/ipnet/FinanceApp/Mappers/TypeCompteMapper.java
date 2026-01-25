package com.ipnet.FinanceApp.Mappers;

import com.ipnet.FinanceApp.DTO.Request.CompteDtoRequest;
import com.ipnet.FinanceApp.DTO.Request.TypeCompteDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.CompteDtoResponse;
import com.ipnet.FinanceApp.DTO.Response.TypeCompteDtoResponse;
import com.ipnet.FinanceApp.Entity.Client;
import com.ipnet.FinanceApp.Entity.Compte;
import com.ipnet.FinanceApp.Entity.TypeCompte;

import jakarta.persistence.EntityNotFoundException;

public class TypeCompteMapper {
	
	public TypeCompte dtoToTypeCompte(TypeCompteDtoRequest tcp) {
		TypeCompte typeCompte = new TypeCompte();
		typeCompte.setLibelle(tcp.libelle());
		return typeCompte;
	}
	
	public TypeCompteDtoResponse typeCompteToDto(TypeCompte typeCompte) {
		TypeCompteDtoResponse response = 
				new TypeCompteDtoResponse
				(
					typeCompte.getPublicId(),
					typeCompte.getLibelle()
				);
		return response;
	}


}
