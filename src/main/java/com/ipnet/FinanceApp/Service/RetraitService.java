package com.ipnet.FinanceApp.Service;

import java.util.List;
import java.util.UUID;

import com.ipnet.FinanceApp.DTO.Request.RetraitDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.RetraitDtoResponse;

public interface RetraitService {
	public RetraitDtoResponse create(RetraitDtoRequest rt);
	public RetraitDtoResponse update(RetraitDtoRequest rt, UUID id);
	public List<RetraitDtoResponse> liste();
	public void delete(UUID id);
	public RetraitDtoResponse detailRetrait(UUID id);
}
