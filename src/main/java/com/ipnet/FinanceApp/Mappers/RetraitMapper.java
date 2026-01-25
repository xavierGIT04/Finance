package com.ipnet.FinanceApp.Mappers;

import com.ipnet.FinanceApp.DTO.Request.RetraitDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.RetraitDtoResponse;
import com.ipnet.FinanceApp.Entity.Client;
import com.ipnet.FinanceApp.Entity.Compte;
import com.ipnet.FinanceApp.Entity.Retrait;
import com.ipnet.FinanceApp.Repository.ClientRepository;
import com.ipnet.FinanceApp.Repository.CompteRepository;

import jakarta.persistence.EntityNotFoundException;

public class RetraitMapper {
	private final CompteRepository compteRepository;
	private final ClientRepository clientRepository;
	
	public RetraitMapper(CompteRepository compteRepository, ClientRepository clientRepository) {
		super();
		this.compteRepository = compteRepository;
		this.clientRepository = clientRepository;
	}

	public Retrait dtoToRetrait(RetraitDtoRequest rt) {
		Retrait retrait = new Retrait();
		Compte compte = compteRepository.findByPublicId(rt.compte()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));
		Client client = clientRepository.findByPublicId(rt.client()).orElseThrow(() -> new EntityNotFoundException("Client Not found"));

		retrait.setClient(client);
		retrait.setCompte(compte);
		retrait.setMontant(rt.montant());
		
		return retrait;
	}
	

	public RetraitDtoResponse retraitToDto(Retrait retrait) {
		RetraitDtoResponse response = 
				new RetraitDtoResponse
				(
					retrait.getPublicId(),
					retrait.getMontant(),
					retrait.getCompte().getNumero(),
					retrait.getClient().getNom()+"-"+retrait.getClient().getPrenom()
					
				);
		return response;
	}

}
