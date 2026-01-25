package com.ipnet.FinanceApp.Service;

import java.util.List;
import java.util.UUID;

import com.ipnet.FinanceApp.DTO.Request.CompteDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.CompteDtoResponse;


public interface CompteService {
	public CompteDtoResponse create(CompteDtoRequest c);
	public CompteDtoResponse update(CompteDtoRequest c, UUID id);
	public List<CompteDtoResponse> liste();
	public void delete(UUID id);
	public CompteDtoResponse detailCompte(UUID id);
}
