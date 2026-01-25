package com.ipnet.FinanceApp.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ipnet.FinanceApp.DTO.Request.ClientDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.ClientDtoResponse;
import com.ipnet.FinanceApp.Service.ClientService;
import com.ipnet.FinanceApp.utils.BaseResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/client/")
@SecurityRequirement(name = "Bearer Authentication")
public class ClientController {
	
	@Autowired
	ClientService clientServiceImpl;
	
	@GetMapping("/")
	public BaseResponse<List<ClientDtoResponse>> clients(){
		BaseResponse<List<ClientDtoResponse>> response = new BaseResponse<List<ClientDtoResponse>>();
		response.setData(clientServiceImpl.liste());
		response.setDescription("Liste des clients récupérés avec succès");
		response.setStatus(200);
		return response;
	}
	
	@GetMapping("/{id}/")
	public  BaseResponse<ClientDtoResponse> clientDetail(@PathVariable UUID id){
		BaseResponse<ClientDtoResponse> response = new BaseResponse<ClientDtoResponse>();
		response.setData(clientServiceImpl.detailClient(id));
		response.setDescription("Détail du client récupérés avec succès");
		response.setStatus(200);
		return response;
	}
	
	@PostMapping("/")
	public BaseResponse<ClientDtoResponse> addClient(@RequestBody ClientDtoRequest client) {
		BaseResponse<ClientDtoResponse> response = new BaseResponse<ClientDtoResponse>();
		response.setData(clientServiceImpl.create(client));
		response.setDescription("client créé avec succès");
		response.setStatus(200);
		return response;
	}
	
	@PutMapping("/{id}/")
	public BaseResponse<ClientDtoResponse> update(@RequestBody ClientDtoRequest client, @PathVariable UUID id) {
		BaseResponse<ClientDtoResponse> response = new BaseResponse<ClientDtoResponse>();
		response.setData(clientServiceImpl.update(client, id));
		response.setDescription("client modifié avec succès");
		response.setStatus(200);
		return response;
	}
	
	@DeleteMapping("/{id}/")
	public void deleteClient(@PathVariable UUID id) {
		clientServiceImpl.delete(id);
	}
	
	
}
