package com.ipnet.FinanceApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ipnet.FinanceApp.DTO.Request.CompteDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.CompteDtoResponse;
import com.ipnet.FinanceApp.Entity.Client;
import com.ipnet.FinanceApp.Entity.Compte;
import com.ipnet.FinanceApp.Entity.TypeCompte;
import com.ipnet.FinanceApp.Mappers.CompteMapper;
import com.ipnet.FinanceApp.Repository.CompteRepository;
import com.ipnet.FinanceApp.Repository.TypeCompteRepository;

import jakarta.transaction.Transactional;

import com.ipnet.FinanceApp.Repository.ClientRepository;

@Transactional
@Service
public class CompteServiceImpl implements CompteService{

	
	private final CompteRepository compteRepository;
	private final ClientRepository clientRepository;
	private final TypeCompteRepository typeCompteRepository;
	
	public CompteServiceImpl(CompteRepository compteRepository, ClientRepository clientRepository, TypeCompteRepository typeCompteRepository) {
		this.compteRepository = compteRepository;
		this.clientRepository = clientRepository;
		this.typeCompteRepository = typeCompteRepository;
	}
	

	@Override
	public CompteDtoResponse create(CompteDtoRequest c) {
		CompteMapper cm = new CompteMapper(clientRepository, typeCompteRepository);
		Client client = clientRepository.findByPublicId(c.proprietaire()).orElseThrow(()-> new RuntimeException("Client not found"));
		TypeCompte typeCompte = typeCompteRepository.findByPublicId(c.typeCompte()).orElseThrow(()-> new RuntimeException("not found"));
		
		Compte compte = new Compte();
		compte = cm.dtoToCompte(c);
		Compte compteSave = compteRepository.save(compte);
		
		client.getComptes().add(compteSave);
		typeCompte.getComptes().add(compteSave);
		
		return cm.compteToDto(compteSave);
	}


	@Override
	public CompteDtoResponse update(CompteDtoRequest c, UUID id) {
		Compte compte = compteRepository.findByPublicId(id).orElseThrow(()-> new RuntimeException("not found"));
		TypeCompte typeCompte = typeCompteRepository.findByPublicId(c.typeCompte()).orElseThrow(()-> new RuntimeException("not found"));
		Client client = clientRepository.findByPublicId(c.proprietaire()).orElseThrow(()-> new RuntimeException("Client not found"));

		compte.setSolde(c.solde());
		compte.setTypeCompte(typeCompte);
		
		if(!compte.getProprietaire().equals(client)) {
			compte.getProprietaire().getComptes().remove(compte);

			compte.setProprietaire(client);
			
			compte.getProprietaire().getComptes().add(compte);
				
		}
		CompteMapper cm = new CompteMapper(clientRepository, typeCompteRepository);

		return  cm.compteToDto(compteRepository.save(compte));
	}


	@Override
	public void delete(UUID id) {
		Compte compte = compteRepository.findByPublicId(id).orElseThrow(()-> new RuntimeException("not found"));
		compteRepository.delete(compte);
	}


	@Override
	public CompteDtoResponse detailCompte(UUID id) {
		Compte compte = compteRepository.findByPublicId(id).orElseThrow(()-> new RuntimeException("not found"));
		CompteMapper cm = new CompteMapper(clientRepository, typeCompteRepository);

		return cm.compteToDto(compte);
	}


	@Override
	public List<CompteDtoResponse> liste() {
		List<Compte> comptes = compteRepository.findAll();
		List<CompteDtoResponse> comptesDto = new ArrayList<>();
		CompteMapper cm = new CompteMapper(clientRepository, typeCompteRepository);

		for (Compte compte : comptes) {
			comptesDto.add(cm.compteToDto(compte));
		}
		return comptesDto;
	}

}
