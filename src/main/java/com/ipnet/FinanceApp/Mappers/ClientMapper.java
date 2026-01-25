package com.ipnet.FinanceApp.Mappers;

import com.ipnet.FinanceApp.DTO.Request.ClientDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.ClientDtoResponse;
import com.ipnet.FinanceApp.Entity.Client;

public class ClientMapper {
	
	public Client dtoToClient(ClientDtoRequest cl) {
		Client client = new Client();
		client.setNom(cl.nom());
		client.setPrenom(cl.prenom());
		client.setAdresse(cl.adresse());
		client.setTelephone(cl.telephone());
		return client;
	}
	
	public ClientDtoResponse clientToDto(Client client) {
		ClientDtoResponse response = 
				new ClientDtoResponse
				(
					client.getPublicId(),
					client.getNom(),
					client.getPrenom(),
					client.getAdresse(),
					client.getTelephone()
				);
		return response;
	}
}
