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

import com.ipnet.FinanceApp.DTO.Request.CompteDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.CompteDtoResponse;
import com.ipnet.FinanceApp.Service.CompteService;
import com.ipnet.FinanceApp.utils.BaseResponse;

@RestController
@RequestMapping("/compte/")
public class CompteController {
	
	@Autowired
	CompteService compteService;
	
	@GetMapping("/")
	public BaseResponse<List<CompteDtoResponse>> comptes(){
		BaseResponse<List<CompteDtoResponse>> response = new BaseResponse<>();
		response.setData(compteService.liste());
		response.setDescription("listes des comptes récupérés avec succès");
		response.setStatus(200);
		return response;
	}
	
	@GetMapping("/{id}/")
	public BaseResponse<CompteDtoResponse> getCompte(@PathVariable UUID id) {
		BaseResponse<CompteDtoResponse> response = new BaseResponse<>();
		response.setData(compteService.detailCompte(id));
		response.setDescription("Détail du compte récupéré avec succès");
		response.setStatus(200);
		return response;
		
	}
	
	@PostMapping("/")
	public BaseResponse<CompteDtoResponse> addCompte(@RequestBody CompteDtoRequest c) {
		BaseResponse<CompteDtoResponse> response = new BaseResponse<>();
		response.setData(compteService.create(c));
		response.setDescription("compte créé avec succès");
		response.setStatus(200);
		return response;
	}
	
	@PutMapping("/{id}/")
	public BaseResponse<CompteDtoResponse> updateCompte(@RequestBody CompteDtoRequest c, @PathVariable UUID id) {
		BaseResponse<CompteDtoResponse> response = new BaseResponse<>();
		response.setData(compteService.update(c, id));
		response.setDescription("compte modifié avec succès");
		response.setStatus(200);
		return response;
	}
	
	@DeleteMapping("/{id}/")
	public void deleteCompte(@PathVariable UUID id) {
		compteService.delete(id);
	}

}
