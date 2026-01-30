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

import com.ipnet.FinanceApp.DTO.Request.TypeCompteDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.TypeCompteDtoResponse;
import com.ipnet.FinanceApp.Service.TypeCompteService;
import com.ipnet.FinanceApp.utils.BaseResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/type-compte/")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Types de Comptes", description = "API de gestion des types de comptes")
public class TypeCompteController {

	@Autowired
	TypeCompteService typeCompteService;
	
	@GetMapping("/")
	public  BaseResponse<List<TypeCompteDtoResponse>> typesCompte(){
		BaseResponse<List<TypeCompteDtoResponse>> response = new BaseResponse<>();
		response.setData(typeCompteService.liste());
		response.setDescription("listes des catégories de comptes récupérés avec succès");
		response.setStatus(200);
		return response;
	}
	
	@GetMapping("/{id}/")
	public BaseResponse<TypeCompteDtoResponse> getTypesCompte(@PathVariable UUID id) {
		BaseResponse<TypeCompteDtoResponse> response = new BaseResponse<>();
		response.setData(typeCompteService.detailTypeCompte(id));
		response.setDescription("Détail du Type récupéré avec succès");
		response.setStatus(200);
		return response;
		
	}
	
	@PostMapping("/")
	public BaseResponse<TypeCompteDtoResponse> addTypeCompte(@RequestBody TypeCompteDtoRequest t) {
		BaseResponse<TypeCompteDtoResponse> response = new BaseResponse<>();
		response.setData(typeCompteService.create(t));
		response.setDescription("Type créé avec succès");
		response.setStatus(200);
		return response;
	}
	
	@PutMapping("/{id}/")
	public BaseResponse<TypeCompteDtoResponse> updateTypeCompte(@RequestBody TypeCompteDtoRequest t, @PathVariable UUID id) {
		BaseResponse<TypeCompteDtoResponse> response = new BaseResponse<>();
		response.setData(typeCompteService.update(t, id));
		response.setDescription("Type modifié avec succès");
		response.setStatus(200);
		return response;
	}
	
	@DeleteMapping("/{id}/")
	public void deleteTypeCompte(@PathVariable UUID id) {
		typeCompteService.delete(id);
	}
}
