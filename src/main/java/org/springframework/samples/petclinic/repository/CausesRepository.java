package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Causes;

public interface CausesRepository extends Repository<Causes, Integer> {
	
	public void deleteCauseById(@Param("id") int id);
	
	public void save(Causes cause);
	
	public Optional<Causes> findById(@Param("id") int id);
	
	public Collection<Causes> findAll(); 
}
