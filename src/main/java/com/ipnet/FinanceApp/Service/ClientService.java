package com.ipnet.FinanceApp.Service;

import java.util.List;
import java.util.UUID;

import com.ipnet.FinanceApp.DTO.Request.ClientDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.ClientDtoResponse;

public interface ClientService {
	
	public ClientDtoResponse create(ClientDtoRequest cli);
	public ClientDtoResponse update(ClientDtoRequest cli, UUID id);
	public List<ClientDtoResponse> liste();
	public void delete(UUID id);
	public ClientDtoResponse detailClient(UUID id);
}
