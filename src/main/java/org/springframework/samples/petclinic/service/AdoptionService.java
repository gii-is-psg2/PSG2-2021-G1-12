package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.repository.AdoptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdoptionService {
	
	private AdoptionRepository adoptionRepository;


	public AdoptionService(AdoptionRepository adoptionRepository) {
		this.adoptionRepository = adoptionRepository;
	}

	@Transactional(readOnly = true)
	public Adoption findAdoptionById(int id) throws DataAccessException {
		return adoptionRepository.findById(id);
	}

	public void saveAdoption(Adoption petSeraAdoptada) {
		this.adoptionRepository.save(petSeraAdoptada);
		
	}

	public List<Adoption> findAll() {
		return this.adoptionRepository.findAll();
	}



}
