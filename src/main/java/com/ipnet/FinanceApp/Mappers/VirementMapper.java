package com.ipnet.FinanceApp.Mappers;

import com.ipnet.FinanceApp.DTO.Request.RetraitDtoRequest;
import com.ipnet.FinanceApp.DTO.Request.VirementDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.RetraitDtoResponse;
import com.ipnet.FinanceApp.DTO.Response.VirementDtoResponse;
import com.ipnet.FinanceApp.Entity.Client;
import com.ipnet.FinanceApp.Entity.Compte;
import com.ipnet.FinanceApp.Entity.Retrait;
import com.ipnet.FinanceApp.Entity.Virement;
import com.ipnet.FinanceApp.Repository.ClientRepository;
import com.ipnet.FinanceApp.Repository.CompteRepository;

import jakarta.persistence.EntityNotFoundException;

public class VirementMapper {

	private final CompteRepository compteRepository;
	private final ClientRepository clientRepository;
	public VirementMapper(CompteRepository compteRepository, ClientRepository clientRepository) {
		super();
		this.compteRepository = compteRepository;
		this.clientRepository = clientRepository;
	}
	
	public Virement dtoToVirement(VirementDtoRequest vr) {
		Virement virement = new Virement();
		Compte compteS = compteRepository.findByPublicId(vr.compteSource()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));
		Compte compteD = compteRepository.findByPublicId(vr.compteDestination()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));
		Client client = clientRepository.findByPublicId(vr.client()).orElseThrow(() -> new EntityNotFoundException("Client Not found"));
		virement.setClient(client);
		virement.setCompteSource(compteS);
		virement.setCompteDestination(compteD);
		virement.setMontant(vr.montant());
		
		return virement;
	}
	

	public VirementDtoResponse virementDtoResponseToDto(Virement virement) {
		VirementDtoResponse response = 
				new VirementDtoResponse
				(
					virement.getPublicId(),
					virement.getMontant(),
					virement.getCompteSource().getNumero(),
					virement.getCompteDestination().getNumero(),
					virement.getClient().getNom()+"-"+virement.getClient().getPrenom()
					
				);
		return response;
	}

}
