package com.ipnet.FinanceApp.Service;

import java.util.List;
import java.util.UUID;

import com.ipnet.FinanceApp.DTO.Request.VirementDtoRequest;
import com.ipnet.FinanceApp.DTO.Response.VirementDtoResponse;

public interface VirementService {
	public VirementDtoResponse create(VirementDtoRequest vr);
	public VirementDtoResponse update(VirementDtoRequest vr, UUID id);
	public List<VirementDtoResponse> liste();
	public void delete(UUID id);
	public VirementDtoResponse detailVirement(UUID id);
}
