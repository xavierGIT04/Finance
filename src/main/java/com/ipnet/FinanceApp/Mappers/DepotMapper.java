package com.ipnet.FinanceApp.Mappers;

import com.ipnet.FinanceApp.DTO.Request.DepotDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.DepotDtoResponse;
import com.ipnet.FinanceApp.Entity.Compte;
import com.ipnet.FinanceApp.Entity.Depot;
import com.ipnet.FinanceApp.Repository.CompteRepository;

import jakarta.persistence.EntityNotFoundException;

public class DepotMapper {
	
	private final CompteRepository compteRepository;

	public DepotMapper(CompteRepository compteRepository) {
		super();
		this.compteRepository = compteRepository;
	}

	public Depot dtoToDepot(DepotDtoRequest dpr) {
		Depot depot = new Depot();
		Compte compte = compteRepository.findByPublicId(dpr.compte()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));
		depot.setNomDeposant(dpr.nomDeposant());
		depot.setAdresseDeposant(dpr.adresseDeposant());
		depot.setTelephoneDeposant(dpr.telephoneDeposant());
		depot.setMontant(dpr.montant());
		depot.setCompte(compte);
		
		return depot;
	}
	
	public DepotDtoResponse depotToDto(Depot depot) {
		DepotDtoResponse response = 
				new DepotDtoResponse
				(
					depot.getPublicId(),
					depot.getNomDeposant(),
					depot.getAdresseDeposant(),
					depot.getTelephoneDeposant(),
					depot.getMontant(),
					depot.getCompte().getNumero()
				);
		return response;
	}

}
