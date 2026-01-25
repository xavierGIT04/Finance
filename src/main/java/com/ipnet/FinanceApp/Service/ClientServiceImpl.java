package com.ipnet.FinanceApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ipnet.FinanceApp.DTO.Request.ClientDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.ClientDtoResponse;
import com.ipnet.FinanceApp.Entity.Client;
import com.ipnet.FinanceApp.Mappers.ClientMapper;
import com.ipnet.FinanceApp.Repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class ClientServiceImpl implements ClientService{

	private static ClientMapper cm = new ClientMapper();
	private final ClientRepository clientRepository;
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	

	@Override
	public ClientDtoResponse create(ClientDtoRequest cli) {
		Client client = new Client();
		client = cm.dtoToClient(cli);
		return cm.clientToDto(clientRepository.save(client));
	}

	@Override
	public ClientDtoResponse update(ClientDtoRequest cli, UUID id) {
		Client client = clientRepository.findByPublicId(id).orElseThrow(() -> new EntityNotFoundException("Client Not found"));
		client.setNom(cli.nom());
		client.setPrenom(cli.prenom());
		client.setAdresse(cli.adresse());
		client.setTelephone(cli.telephone());
		return cm.clientToDto(clientRepository.save(client));
	}

	@Override
	public void delete(UUID id) {
		Client client = clientRepository.findByPublicId(id).orElseThrow(() -> new EntityNotFoundException("Client Not found"));
		clientRepository.delete(client);
	}

	@Override
	public ClientDtoResponse detailClient(UUID id) {
		Client client = clientRepository.findByPublicId(id).orElseThrow(() -> new EntityNotFoundException("Client Not found"));
		return cm.clientToDto(client);
	}


	@Override
	public List<ClientDtoResponse> liste() {
		List<Client> clients = clientRepository.findAll();
		List<ClientDtoResponse> clientsDto = new ArrayList<>();
		for (Client client : clients) {
			clientsDto.add(cm.clientToDto(client));
		}
		return clientsDto;
	}
	


}
