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

import com.ipnet.FinanceApp.DTO.Request.VirementDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.VirementDtoResponse;
import com.ipnet.FinanceApp.Service.VirementService;
import com.ipnet.FinanceApp.utils.BaseResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/virement/")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Virements", description = "API de gestion des virements")
public class VirementController {
	
	@Autowired
	VirementService virementService;
	
	@GetMapping("/")
	public BaseResponse<List<VirementDtoResponse>> virements(){
		BaseResponse<List<VirementDtoResponse>> response = new BaseResponse<>();
		response.setData(virementService.liste());
		response.setDescription("listes des virements récupérés avec succès");
		response.setStatus(200);
		return response;
	}
	
	@GetMapping("/{id}/")
	public BaseResponse<VirementDtoResponse> getVirements(@PathVariable UUID id) {
		BaseResponse<VirementDtoResponse> response = new BaseResponse<>();
		response.setData(virementService.detailVirement(id));
		response.setDescription("Détail du virement récupéré avec succès");
		response.setStatus(200);
		return response;
	}
	
	@PostMapping("/")
	public BaseResponse<VirementDtoResponse> addRetrait(@RequestBody VirementDtoRequest t) {
		BaseResponse<VirementDtoResponse> response = new BaseResponse<>();
		response.setData(virementService.create(t));
		response.setDescription("virement créé avec succès");
		response.setStatus(200);
		return response;	
	}
	
	@PutMapping("/{id}/")
	public BaseResponse<VirementDtoResponse> updateDepot(@RequestBody VirementDtoRequest t, @PathVariable UUID id) {
		BaseResponse<VirementDtoResponse> response = new BaseResponse<>();
		response.setData(virementService.update(t, id));
		response.setDescription("virement modifié avec succès");
		response.setStatus(200);
		return response;	
	}
	
	@DeleteMapping("/{id}/")
	public void deleteRetrait(@PathVariable UUID id) {
		virementService.delete(id);
	}
}
