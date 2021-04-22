package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "adoptions")
public class Adoption extends BaseEntity {
	
	@JoinColumn(name = "pet_id")
	@OneToOne
	private Pet pet;
	
	@JoinColumn(name = "owner_id")
	@OneToOne
	private Owner owner;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy="adoption")
	private List<AdoptionRequest> adoptionRequest;


	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}


}
