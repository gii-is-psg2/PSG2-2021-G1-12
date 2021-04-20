package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Donation;

public interface DonationRepository extends CrudRepository<Donation, Integer>{
	
	@Query("Select d from Donation d where d.cause.id =:id")
	List<Donation> findByCauseId(Integer id);
}
