package com.ipnet.FinanceApp.Service;

import java.util.List;
import java.util.UUID;

import com.ipnet.FinanceApp.DTO.Request.DepotDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.DepotDtoResponse;

public interface DepotService {
	public DepotDtoResponse create(DepotDtoRequest dp);
	public DepotDtoResponse update(DepotDtoRequest dp, UUID id);
	public List<DepotDtoResponse> liste();
	public void delete(UUID id);
	public DepotDtoResponse detailDepot(UUID id);
}
