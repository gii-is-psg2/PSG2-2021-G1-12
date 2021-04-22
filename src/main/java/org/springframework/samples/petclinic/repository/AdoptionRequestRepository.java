package org.springframework.samples.petclinic.repository;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.AdoptionRequest;

public interface AdoptionRequestRepository  extends CrudRepository<AdoptionRequest, Integer>  {

	AdoptionRequest findById(int id) throws DataAccessException;


    @Query("SELECT a FROM AdoptionRequest a")
	List<AdoptionRequest> findAll();
}
