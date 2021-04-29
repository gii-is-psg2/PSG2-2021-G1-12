package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Causes;
import org.springframework.samples.petclinic.model.Owner;

public interface CausesRepository extends CrudRepository<Causes, Integer> {

	
	
}
