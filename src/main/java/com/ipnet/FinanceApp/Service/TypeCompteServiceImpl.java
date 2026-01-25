package com.ipnet.FinanceApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ipnet.FinanceApp.DTO.Request.TypeCompteDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.TypeCompteDtoResponse;
import com.ipnet.FinanceApp.Entity.TypeCompte;
import com.ipnet.FinanceApp.Mappers.TypeCompteMapper;
import com.ipnet.FinanceApp.Repository.TypeCompteRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class TypeCompteServiceImpl implements TypeCompteService{

	private static TypeCompteMapper tm = new TypeCompteMapper();
	private final TypeCompteRepository typeCompteRepository;
	
	public TypeCompteServiceImpl(TypeCompteRepository typeCompteRepository) {
		this.typeCompteRepository = typeCompteRepository;
	}
	

	@Override
	public TypeCompteDtoResponse create(TypeCompteDtoRequest typeC) {
		TypeCompte typeCompte = new TypeCompte();
		typeCompte = tm.dtoToTypeCompte(typeC);
		return tm.typeCompteToDto( typeCompteRepository.save(typeCompte));
	}

	@Override
	public TypeCompteDtoResponse update(TypeCompteDtoRequest typeC, UUID id) {
		TypeCompte typeCompte = typeCompteRepository.findByPublicId(id).orElseThrow(()-> new RuntimeException("not found"));
		typeCompte.setLibelle(typeC.libelle());
		return tm.typeCompteToDto(typeCompteRepository.save(typeCompte));
	}

	@Override
	public void delete(UUID id) {
		TypeCompte typeCompte = typeCompteRepository.findByPublicId(id).orElseThrow(()-> new RuntimeException("not found"));
		typeCompteRepository.delete(typeCompte);
	}

	@Override
	public TypeCompteDtoResponse detailTypeCompte(UUID id) {
		TypeCompte typeCompte = typeCompteRepository.findByPublicId(id).orElseThrow(()-> new RuntimeException("not found"));
		return tm.typeCompteToDto(typeCompte);
	}


	@Override
	public List<TypeCompteDtoResponse> liste() {
		List<TypeCompte> types =typeCompteRepository.findAll();
		List<TypeCompteDtoResponse> typeDto = new ArrayList<>();
		
		for (TypeCompte typeCompt : types) {
			typeDto.add(tm.typeCompteToDto(typeCompt));
		}
		return typeDto;
	}

}
