package com.ipnet.FinanceApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ipnet.FinanceApp.DTO.Request.RetraitDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.RetraitDtoResponse;
import com.ipnet.FinanceApp.Entity.Client;
import com.ipnet.FinanceApp.Entity.Compte;
import com.ipnet.FinanceApp.Entity.Retrait;
import com.ipnet.FinanceApp.Mappers.RetraitMapper;
import com.ipnet.FinanceApp.Repository.ClientRepository;
import com.ipnet.FinanceApp.Repository.CompteRepository;
import com.ipnet.FinanceApp.Repository.RetraitRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class RetraitServiceImpl implements RetraitService {

	private final RetraitRepository retraitRepository;
	private final CompteRepository compteRepository;
	private final ClientRepository clientRepository;

	
	
	public RetraitServiceImpl(RetraitRepository retraitRepository, CompteRepository compteRepository,
			ClientRepository clientRepository) {
		super();
		this.retraitRepository = retraitRepository;
		this.compteRepository = compteRepository;
		this.clientRepository = clientRepository;
	}

	@Override
	public RetraitDtoResponse create(RetraitDtoRequest rt) {
		RetraitMapper rm = new RetraitMapper(compteRepository, clientRepository);
		Compte compte = compteRepository.findByPublicId(rt.compte()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));
		Client client = clientRepository.findByPublicId(rt.client()).orElseThrow(()-> new RuntimeException("Client not found"));

		Retrait retrait = new Retrait();
		retrait = rm.dtoToRetrait(rt);
		float soldeActu = retrait.getCompte().getSolde();
		float montantARetirer = retrait.getMontant();
		
		if(montantARetirer < soldeActu) {
			retrait.getCompte().setSolde(soldeActu-montantARetirer);
		}
		
		Retrait retraitS = retraitRepository.save(retrait);
		compte.getRetraits().add(retraitS);
		client.getRetraits().add(retraitS);
		
		return rm.retraitToDto(retraitS);
	}

	@Override
	public RetraitDtoResponse update(RetraitDtoRequest rt, UUID id) {
		Retrait retrait = retraitRepository.findByPublicId(id).orElseThrow(() -> new EntityNotFoundException("Retrait Not found"));
		RetraitMapper rm = new RetraitMapper(compteRepository, clientRepository);
		Client client = clientRepository.findByPublicId(rt.client()).orElseThrow(()-> new RuntimeException("Client not found"));
		Compte compte = compteRepository.findByPublicId(rt.compte()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));

		if(!compte.equals(retrait.getCompte())) {
			retrait.getCompte().setSolde(retrait.getCompte().getSolde()+retrait.getMontant());
		}
		retrait.setCompte(compte);
		float soldeActu = retrait.getCompte().getSolde();
		float montantARetirer = rt.montant();
		
		if(montantARetirer < soldeActu) {
			retrait.getCompte().setSolde(soldeActu-montantARetirer);
		}
		retrait.setClient(client);
		retrait.setMontant( rt.montant());
		return rm.retraitToDto(retraitRepository.save(retrait));
	}

	@Override
	public List<RetraitDtoResponse> liste() {
		List<Retrait> retraits = retraitRepository.findAll();
		List<RetraitDtoResponse> retraitsD = new ArrayList<>();
		RetraitMapper rm = new RetraitMapper(compteRepository, clientRepository);

		for (Retrait retrait : retraits) {
			retraitsD.add(rm.retraitToDto(retrait));
		}
		return retraitsD;
	}

	@Override
	public void delete(UUID id) {
		Retrait retrait = retraitRepository.findByPublicId(id).orElseThrow(() -> new EntityNotFoundException("Retrait Not found"));
		retraitRepository.delete(retrait);
	}

	@Override
	public RetraitDtoResponse detailRetrait(UUID id) {
		Retrait retrait = retraitRepository.findByPublicId(id).orElseThrow(() -> new EntityNotFoundException("Retrait Not found"));
		RetraitMapper rm = new RetraitMapper(compteRepository, clientRepository);

		return rm.retraitToDto(retrait);
	}

}
