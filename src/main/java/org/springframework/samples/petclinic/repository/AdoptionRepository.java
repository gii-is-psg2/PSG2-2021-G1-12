package org.springframework.samples.petclinic.repository;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.stereotype.Repository;
@Repository
public interface AdoptionRepository  extends CrudRepository<Adoption, Integer>  {

	Adoption findById(int id) throws DataAccessException;


    @Query("SELECT a FROM Adoption a")
	List<Adoption> findAll();

    @Query(value="SELECT * FROM Adoptions WHERE pet_id =?1", nativeQuery=true)
	 Adoption findAdoptionIdByPetId(int petId) throws DataAccessException;
}
