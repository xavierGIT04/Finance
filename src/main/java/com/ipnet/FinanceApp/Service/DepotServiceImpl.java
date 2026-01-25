package com.ipnet.FinanceApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ipnet.FinanceApp.DTO.Request.DepotDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.DepotDtoResponse;
import com.ipnet.FinanceApp.Entity.Compte;
import com.ipnet.FinanceApp.Entity.Depot;
import com.ipnet.FinanceApp.Mappers.DepotMapper;
import com.ipnet.FinanceApp.Repository.CompteRepository;
import com.ipnet.FinanceApp.Repository.DepotRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class DepotServiceImpl implements DepotService {

    private final DepotRepository depotRepository;

	private final CompteRepository compteRepository;

	
	
	public DepotServiceImpl(CompteRepository compteRepository, DepotRepository depotRepository) {
		super();
		this.compteRepository = compteRepository;
		this.depotRepository = depotRepository;
	}

	@Override
	public DepotDtoResponse create(DepotDtoRequest dp) {
		Depot depot = new Depot();
		Compte compte = compteRepository.findByPublicId(dp.compte()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));

		DepotMapper dm = new DepotMapper(compteRepository);
		depot = dm.dtoToDepot(dp);
		depot.getCompte().setSolde(depot.getCompte().getSolde()+depot.getMontant());
		Depot depotS = depotRepository.save(depot);
		compte.getDepots().add(depotS);
		
		return dm.depotToDto(depotS);
	}

	@Override
	public DepotDtoResponse update(DepotDtoRequest dp, UUID id) {
		Depot depot = depotRepository.findByPublicId(id).orElseThrow(()-> new RuntimeException("not found"));
		Compte compte = compteRepository.findByPublicId(dp.compte()).orElseThrow(() -> new EntityNotFoundException("Compte Not found"));

		depot.setAdresseDeposant(dp.adresseDeposant());
		depot.setNomDeposant(dp.nomDeposant());
		depot.setTelephoneDeposant(dp.telephoneDeposant());
		
		depot.getCompte().setSolde(depot.getCompte().getSolde()-depot.getMontant());
		
		compte.setSolde(depot.getCompte().getSolde()+dp.montant());
		depot.setCompte(compte);
		depot.setMontant(dp.montant());
		
		DepotMapper dm = new DepotMapper(compteRepository);

		return dm.depotToDto(depotRepository.save(depot));
	}

	@Override
	public List<DepotDtoResponse> liste() {
		List<Depot> depots = depotRepository.findAll();
		List<DepotDtoResponse> depotsD = new ArrayList<>();
		DepotMapper dm = new DepotMapper(compteRepository);
		
		for (Depot depot : depots) {
			depotsD.add(dm.depotToDto(depot));
		}
		return depotsD;
	}

	@Override
	public void delete(UUID id) {
		Depot depot = depotRepository.findByPublicId(id).orElseThrow(()-> new RuntimeException("not found"));
		depotRepository.delete(depot);
	}

	@Override
	public DepotDtoResponse detailDepot(UUID id) {
		Depot depot = depotRepository.findByPublicId(id).orElseThrow(()-> new RuntimeException("not found"));
		DepotMapper dm = new DepotMapper(compteRepository);

		return dm.depotToDto(depot);
	}

}
