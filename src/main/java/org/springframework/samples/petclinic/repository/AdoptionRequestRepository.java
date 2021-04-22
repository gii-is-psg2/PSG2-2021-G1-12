package org.springframework.samples.petclinic.repository;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.AdoptionRequest;

public interface AdoptionRequestRepository  extends Repository<AdoptionRequest, Integer>  {

	AdoptionRequest findById(int id) throws DataAccessException;

    void save(AdoptionRequest adoptionRequest) throws DataAccessException;
    void delete (AdoptionRequest adoptionRequest) throws DataAccessException;

    @Query("SELECT a FROM AdoptionRequest a")
	List<AdoptionRequest> findAll();
}
