package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.samples.petclinic.repository.AdoptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdoptionService {

	private AdoptionRepository adoptionRepository;
	@Autowired
	private AdoptionRequestService adoptionRequestService;

	@Autowired
	public AdoptionService(AdoptionRepository adoptionRepository) {
		this.adoptionRepository = adoptionRepository;
	}

	@Transactional(readOnly = true)
	public Adoption findAdoptionById(int id) throws DataAccessException {
		return adoptionRepository.findById(id);
	}
	@Transactional
	public void saveAdoption(Adoption petSeraAdoptada) {
		this.adoptionRepository.save(petSeraAdoptada);
		
	}
	@Transactional
	public List<Adoption> findAll() {
		return this.adoptionRepository.findAll();
	}

	@Transactional
	public void deleteAdoption(Adoption adoption) throws DataAccessException {
		List<AdoptionRequest> listaAdopc= this.adoptionRequestService.findAdoptionRequestByAdoptionId(adoption.getId());
		for (int i =0; i<listaAdopc.size();i++) {
			adoptionRequestService.delete(listaAdopc.get(i));
		}
		adoptionRepository.delete(adoption);
	}
	


	
}
