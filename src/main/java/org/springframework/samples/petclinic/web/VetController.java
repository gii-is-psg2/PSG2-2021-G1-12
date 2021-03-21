/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bytebuddy.asm.Advice.This;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final VetService vetService;

	@Autowired
	public VetController(VetService clinicService) {
		this.vetService = clinicService;
	}

	@GetMapping(value = { "/vets" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = { "/vets.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}
	

	@GetMapping(value = { "/vets/{id}/edit"})
	public String editVetCreationForm(Map<String, Object> model,@PathVariable("id") int vetId) {
		Optional<Vet> veterinarianToEdit_Optional = this.vetService.findVetById(vetId);
		
		if(veterinarianToEdit_Optional.isPresent()) {
			Vet veterinarianToEdit = veterinarianToEdit_Optional.get();
			
			model.put("vetToEdit", veterinarianToEdit);
			model.put("allSpecialties", this.vetService.findAllSpecialities());
			return "vets/vetEdit";
		}else {
			return "vets/vetList";
		}
			
	}

	@PostMapping(value = { "/vets/{id}/edit"})
	public String editVetPostForm(Map<String, Object> model,@PathVariable("id") int vetId,@RequestParam("Specialties") String specialties,Vet veterinarianUpdated){
		
		
		List<Specialty> SpecialtiesSelected = this.specialtiesParser_deberiaDeIrEnUnFormatter(specialties);
		for(Specialty s : SpecialtiesSelected) {
			veterinarianUpdated.addSpecialty(s);
		}
		this.vetService.save(veterinarianUpdated);
		return "redirect:/vets";
	}
	
	@GetMapping(value = { "/vets/create"})
	public String vetCreationForm(Map<String, Object> model) {
		
			
		model.put("vetToCreate", new Vet());
		model.put("allSpecialties", this.vetService.findAllSpecialities());

		return "vets/vetCreate";
		
	}
	

	@PostMapping(value = { "/vets/create"})
	public String vetCreationFormPost(Map<String, Object> model,@RequestParam("Specialties") String specialties,Vet vetToCreate) {
		
			
		List<Specialty> SpecialtiesSelected = this.specialtiesParser_deberiaDeIrEnUnFormatter(specialties);
		for(Specialty s : SpecialtiesSelected) {
			vetToCreate.addSpecialty(s);
		}
		this.vetService.save(vetToCreate);			
		return "redirect:/vets";
	}
	
	
	public List<Specialty> specialtiesParser_deberiaDeIrEnUnFormatter(String text){
		List<Specialty> res = new ArrayList<Specialty>();
		
		String[] specialties = text.split(",");
		for(int i=0;i<specialties.length;i++) {
			String stringSpecialty = specialties[i];
			Specialty specialtyParsed = this.vetService.findSpecialtyByName(stringSpecialty);
			res.add(specialtyParsed);
		}
		
		return res;
	}
	
	
	@GetMapping("/vets/{vetId}/delete")
	public String processDeleteVet(@PathVariable("vetId") int vetId) {
		Vet vet = this.vetService.findRealVetById(vetId);
		this.vetService.deleteVet(vet);
		return "redirect:/vets";
	}
}
