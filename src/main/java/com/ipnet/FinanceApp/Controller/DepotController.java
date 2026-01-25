package com.ipnet.FinanceApp.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipnet.FinanceApp.DTO.Request.DepotDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.DepotDtoResponse;
import com.ipnet.FinanceApp.Service.DepotService;
import com.ipnet.FinanceApp.utils.BaseResponse;

@RestController
@RequestMapping("/depot/")
public class DepotController {
	@Autowired
	DepotService depotService;
	
	@GetMapping("/")
	public BaseResponse<List<DepotDtoResponse>> depots(){
		BaseResponse<List<DepotDtoResponse>> response = new BaseResponse<>();
		response.setData(depotService.liste());
		response.setDescription("listes des dépots récupérés avec succès");
		response.setStatus(200);
		return response;
	}
	
	@GetMapping("/{id}/")
	public BaseResponse<DepotDtoResponse> getDepots(@PathVariable UUID id) {
		BaseResponse<DepotDtoResponse> response = new BaseResponse<>();
		response.setData(depotService.detailDepot(id));
		response.setDescription("Détail du dépot récupérés avec succès");
		response.setStatus(200);
		return response;
	}
	
	@PostMapping("/")
	public BaseResponse<DepotDtoResponse> addDepot(@RequestBody DepotDtoRequest t) {
		BaseResponse<DepotDtoResponse> response = new BaseResponse<>();
		response.setData(depotService.create(t));
		response.setDescription("dépot créé avec succès");
		response.setStatus(200);
		return response;	
	}
	
	@PutMapping("/{id}/")
	public BaseResponse<DepotDtoResponse> updateDepot(@RequestBody DepotDtoRequest t, @PathVariable UUID id) {
		BaseResponse<DepotDtoResponse> response = new BaseResponse<>();
		response.setData(depotService.update(t, id));
		response.setDescription("dépot modifié avec succès");
		response.setStatus(200);
		return response;	
	}
	
	@DeleteMapping("/{id}/")
	public void deleteTypeTransaction(@PathVariable UUID id) {
		depotService.delete(id);
	}
}
