package com.ipnet.FinanceApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ipnet.FinanceApp.DTO.Request.VirementDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.VirementDtoResponse;
import com.ipnet.FinanceApp.Entity.Client;
import com.ipnet.FinanceApp.Entity.Compte;
import com.ipnet.FinanceApp.Entity.Virement;
import com.ipnet.FinanceApp.Mappers.VirementMapper;
import com.ipnet.FinanceApp.Repository.ClientRepository;
import com.ipnet.FinanceApp.Repository.CompteRepository;
import com.ipnet.FinanceApp.Repository.VirementRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VirementServiceImpl implements VirementService{

	private final VirementRepository virementRepository;
	private final CompteRepository compteRepository;
	private final ClientRepository clientRepository;
	
	public VirementServiceImpl(VirementRepository virementRepository, CompteRepository compteRepository,
			ClientRepository clientRepository) {
		super();
		this.virementRepository = virementRepository;
		this.compteRepository = compteRepository;
		this.clientRepository = clientRepository;
	}

	@Override
	public VirementDtoResponse create(VirementDtoRequest vr) {
		Virement virement = new Virement();
		VirementMapper vm = new VirementMapper(compteRepository, clientRepository);
		Compte compteS = compteRepository.findByPublicId(vr.compteSource()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));
		Compte compteD = compteRepository.findByPublicId(vr.compteDestination()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));
		Client client = clientRepository.findByPublicId(vr.client()).orElseThrow(()-> new RuntimeException("Client not found"));

		virement = vm.dtoToVirement(vr);
		float montantAVirer = virement.getMontant();
		if(compteS.getSolde() > montantAVirer) {
			virement.getCompteSource().setSolde(virement.getCompteSource().getSolde() - montantAVirer);
			virement.getCompteDestination().setSolde(virement.getCompteDestination().getSolde() + montantAVirer);
		}
		compteS.getVirementsApartirDeCeCompte().add(virement);
		compteD.getVirementsAdestinationDeCeCompte().add(virement);
		client.getVirements().add(virement);
		
		return vm.virementDtoResponseToDto(virementRepository.save(virement));
	}

	@Override
	public VirementDtoResponse update(VirementDtoRequest vr, UUID id) {
		VirementMapper vm = new VirementMapper(compteRepository, clientRepository);
		Virement virement = virementRepository.findByPublicId(id).orElseThrow(() -> new EntityNotFoundException("Virement Not found"));
		Compte compteS = compteRepository.findByPublicId(vr.compteSource()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));
		Compte compteD = compteRepository.findByPublicId(vr.compteDestination()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));
		Client client = clientRepository.findByPublicId(vr.client()).orElseThrow(()-> new RuntimeException("Client not found"));
		
		virement.setClient(client);
		virement.setCompteSource(compteS);
		virement.setCompteDestination(compteD);
		virement.setMontant(vr.montant());
		virement.getCompteSource().setSolde(virement.getCompteSource().getSolde() - virement.getMontant() );
		virement.getCompteDestination().setSolde(virement.getCompteSource().getSolde() + virement.getMontant() );

		return vm.virementDtoResponseToDto(virementRepository.save(virement));
	}

	@Override
	public List<VirementDtoResponse> liste() {
		List<Virement> virements = virementRepository.findAll();
		List<VirementDtoResponse> virementsD = new ArrayList<>();
		VirementMapper vm = new VirementMapper(compteRepository, clientRepository);

		for (Virement virement : virements) {
			virementsD.add(vm.virementDtoResponseToDto(virement));
		}
		return virementsD;
	}

	@Override
	public void delete(UUID id) {
		Virement virement = virementRepository.findByPublicId(id).orElseThrow(() -> new EntityNotFoundException("Virement Not found"));
		virementRepository.delete(virement);;
		
	}

	@Override
	public VirementDtoResponse detailVirement(UUID id) {
		Virement virement = virementRepository.findByPublicId(id).orElseThrow(() -> new EntityNotFoundException("Virement Not found"));
		VirementMapper vm = new VirementMapper(compteRepository, clientRepository);

		return vm.virementDtoResponseToDto(virement);
	}

}
