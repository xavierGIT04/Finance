package com.ipnet.FinanceApp.Service;

import java.util.List;
import java.util.UUID;

import com.ipnet.FinanceApp.DTO.Request.TypeCompteDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.TypeCompteDtoResponse;

public interface TypeCompteService {
	public TypeCompteDtoResponse create(TypeCompteDtoRequest typeC);
	public TypeCompteDtoResponse update(TypeCompteDtoRequest typeC, UUID id);
	public List<TypeCompteDtoResponse> liste();
	public void delete(UUID id);
	public TypeCompteDtoResponse detailTypeCompte(UUID id);
}
