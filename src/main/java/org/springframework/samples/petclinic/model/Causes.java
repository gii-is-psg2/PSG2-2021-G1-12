package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "causes")
public class Causes extends NamedEntity{

	
	@Column(name = "description")
	@NotEmpty
	private String description;
	
	@Column(name = "budget_target")
	@NotNull
	private Double budgetTarget;
	
	
	@Column(name = "organisation")
	@NotEmpty
	private String organisation;
	
	@Column(name = "active")
	@NotNull
	private Boolean active;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getBudgetTarget() {
		return budgetTarget;
	}

	public void setBudgetTarget(Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Causes [description=" + description + ", budgetTarget=" + budgetTarget + ", organisation="
				+ organisation + ", active=" + active + "]";
	}
	
	
	
}
