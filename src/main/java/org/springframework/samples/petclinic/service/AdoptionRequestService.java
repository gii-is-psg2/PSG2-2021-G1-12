package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.AdoptionRepository;
import org.springframework.samples.petclinic.repository.AdoptionRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdoptionRequestService {
	
	
	private AdoptionRequestRepository adoptionRequestRepository;
	@Autowired
	private AdoptionRepository adoptionRepository;

	
	public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository,AdoptionRepository adoptionRepository) {
		this.adoptionRequestRepository = adoptionRequestRepository;
		this.adoptionRepository =adoptionRepository;
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
	
	@Transactional
	public List<AdoptionRequest> findAdoptionRequestByIdPet(int petId) {
		Adoption adop= 	this.adoptionRepository.findAdoptionIdByPetId(petId);
		List<AdoptionRequest> res= this.adoptionRequestRepository.findAdoptionRequestByAdoptionId(adop.getId());

		return res;
	}

	@Transactional
	public void deleteAdoptionRequest(AdoptionRequest adoptionRequest) throws DataAccessException {
		adoptionRequestRepository.delete(adoptionRequest);
	}
	
	@Transactional
	public List<AdoptionRequest> findAdoptionRequestByAdoptionId(int adoptionId) {
		return adoptionRequestRepository.findAdoptionRequestByAdoptionId(adoptionId);

		}

	@Transactional
	public void delete(AdoptionRequest adoptionRequest) throws DataAccessException {
		adoptionRequestRepository.delete(adoptionRequest);
	}



}
