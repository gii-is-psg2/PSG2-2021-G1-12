package org.springframework.samples.petclinic.model;

import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "adoptions")
public class Adoption extends BaseEntity {

	@Column(name = "description")
	private String descripcion;
	
	@JoinColumn(name = "pet_id")
	@OneToOne
	private Pet pet;
	
	@JoinColumn(name = "owner_id")
	@OneToOne
	private Owner owner;


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
