package org.springframework.samples.petclinic.repository;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.stereotype.Repository;
@Repository
public interface AdoptionRequestRepository  extends CrudRepository<AdoptionRequest, Integer>  {

	AdoptionRequest findById(int id) throws DataAccessException;

    @Query("SELECT a FROM AdoptionRequest a")
	List<AdoptionRequest> findAll();
    
    @Query(value="SELECT * FROM Adoption_Request WHERE adoption_id =?1", nativeQuery=true)	
    List<AdoptionRequest> findAdoptionRequestByAdoptionId(int adoptionId) throws DataAccessException;



}
