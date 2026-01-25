package com.ipnet.FinanceApp.Mappers;


import com.ipnet.FinanceApp.DTO.Request.CompteDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.CompteDtoResponse;
import com.ipnet.FinanceApp.Entity.Client;
import com.ipnet.FinanceApp.Entity.Compte;
import com.ipnet.FinanceApp.Entity.TypeCompte;
import com.ipnet.FinanceApp.Repository.ClientRepository;
import com.ipnet.FinanceApp.Repository.TypeCompteRepository;

import jakarta.persistence.EntityNotFoundException;

public class CompteMapper {
	
	private final ClientRepository clientRepository;
	private final TypeCompteRepository typeCompteRepository;
	
	public CompteMapper(ClientRepository cl, TypeCompteRepository tp) {
		super();
		this.clientRepository = cl;
		this.typeCompteRepository = tp;
	}

	public Compte dtoToCompte(CompteDtoRequest cp) {
		Compte compte = new Compte();
		Client client = clientRepository.findByPublicId(cp.proprietaire()).orElseThrow(() -> new EntityNotFoundException("Client Not found"));
		TypeCompte typeC = typeCompteRepository.findByPublicId(cp.typeCompte()).orElseThrow(() -> new EntityNotFoundException("TypeCompte Not found"));
		compte.setSolde(cp.solde());
		compte.setNumero(cp.numero());
		compte.setTypeCompte(typeC);
		compte.setProprietaire(client);
		
		return compte;
	}
	
	public CompteDtoResponse compteToDto(Compte compte) {
		CompteDtoResponse response = 
				new CompteDtoResponse
				(
					compte.getPublicId(),
					compte.getNumero(),
					compte.getSolde(),
					compte.getTypeCompte().getLibelle(),
					compte.getProprietaire().getNom()+"-"+compte.getProprietaire().getPrenom()
				);
		return response;
	}

}
