package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.samples.petclinic.repository.AdoptionRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdoptionRequestService {
	
	private AdoptionRequestRepository adoptionRequestRepository;


	public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository) {
		this.adoptionRequestRepository = adoptionRequestRepository;
	}

	@Transactional(readOnly = true)
	public AdoptionRequest findAdoptionRequestById(int id) throws DataAccessException {
		return adoptionRequestRepository.findById(id);
	}
	@Transactional
	public void saveAdoptionRequest(AdoptionRequest petSeraAdoptada) {
		this.adoptionRequestRepository.save(petSeraAdoptada);
		
	}
	@Transactional
	public List<AdoptionRequest> findAll() {
		return this.adoptionRequestRepository.findAll();
	}



}
