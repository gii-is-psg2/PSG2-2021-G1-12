package org.springframework.samples.petclinic.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causes;
import org.springframework.samples.petclinic.repository.CausesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CausesService {

	@Autowired
	private CausesRepository causesRepository;	
	
	@Transactional
	public void save(Causes cause) {
		this.causesRepository.save(cause);
	}
	
	@Transactional
	public void deleteCauseById(Integer id) {
		this.causesRepository.deleteCauseById(id);
	}
	
	@Transactional
	public void setActiveFalse(Causes cause) {
		cause.setActive(false);
		this.save(cause);
	}
	
	
	public Optional<Causes> findById(Integer id) {
		
		return this.causesRepository.findById(id);
	}
	
	
	public List<Causes> findAll() {
		List<Causes> list = new ArrayList<Causes>();
		
		this.causesRepository.findAll().forEach(list::add);
		
		return list;
	}
	
}
