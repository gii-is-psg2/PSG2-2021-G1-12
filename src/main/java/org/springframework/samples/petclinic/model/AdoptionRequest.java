package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "adoptionRequest")
public class AdoptionRequest extends BaseEntity {
	
	@JoinColumn(name = "owner_id")
	@ManyToOne
	private Owner owner;
	
	@Column(name = "description")
	@NotNull
	private String descripcion;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "adoption")
	private Adoption adoption;

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Adoption getAdoption() {
		return adoption;
	}

	public void setAdoption(Adoption adoption) {
		this.adoption = adoption;
	}

	@Override
	public String toString() {
		return "AdoptionRequest [owner=" + owner + ", descripcion=" + descripcion + ", adoption=" + adoption + "]";
	}
	
	
	
	
}
