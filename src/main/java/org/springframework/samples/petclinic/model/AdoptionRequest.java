package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "adoptionRequest")
public class AdoptionRequest extends BaseEntity {
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "owner_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Owner owner;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@Column(name = "description")
	private String description;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne (cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "adoption_id")
	private Adoption adoption;

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Adoption getAdoption() {
		return adoption;
	}

	public void setAdoption(Adoption adoption) {
		this.adoption = adoption;
	}

	
}
