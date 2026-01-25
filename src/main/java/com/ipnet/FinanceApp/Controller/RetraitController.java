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

import com.ipnet.FinanceApp.DTO.Request.RetraitDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.RetraitDtoResponse;
import com.ipnet.FinanceApp.Service.RetraitService;
import com.ipnet.FinanceApp.utils.BaseResponse;

@RestController
@RequestMapping("/retrait/")
public class RetraitController {

	@Autowired
	RetraitService retraitService;
	
	@GetMapping("/")
	public BaseResponse<List<RetraitDtoResponse>> retraits(){
		BaseResponse<List<RetraitDtoResponse>> response = new BaseResponse<>();
		response.setData(retraitService.liste());
		response.setDescription("listes des retraits récupérés avec succès");
		response.setStatus(200);
		return response;
	}
	
	@GetMapping("/{id}/")
	public BaseResponse<RetraitDtoResponse> getRetraits(@PathVariable UUID id) {
		BaseResponse<RetraitDtoResponse> response = new BaseResponse<>();
		response.setData(retraitService.detailRetrait(id));
		response.setDescription("Détail du retrait récupérés avec succès");
		response.setStatus(200);
		return response;
	}
	
	@PostMapping("/")
	public BaseResponse<RetraitDtoResponse> addRetrait(@RequestBody RetraitDtoRequest t) {
		BaseResponse<RetraitDtoResponse> response = new BaseResponse<>();
		response.setData(retraitService.create(t));
		response.setDescription("retrait créé avec succès");
		response.setStatus(200);
		return response;	
	}
	
	@PutMapping("/{id}/")
	public BaseResponse<RetraitDtoResponse> updateDepot(@RequestBody RetraitDtoRequest t, @PathVariable UUID id) {
		BaseResponse<RetraitDtoResponse> response = new BaseResponse<>();
		response.setData(retraitService.update(t, id));
		response.setDescription("retrait modifié avec succès");
		response.setStatus(200);
		return response;	
	}
	
	@DeleteMapping("/{id}/")
	public void deleteRetrait(@PathVariable UUID id) {
		retraitService.delete(id);
	}
}
