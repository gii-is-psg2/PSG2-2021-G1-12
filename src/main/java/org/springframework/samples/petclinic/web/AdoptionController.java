package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdoptionController {
	private static final String VIEWS_DESCRIPTION = "adoptions/adoptionEdit";

	
	private final PetService petService;
    private final AdoptionService adoptionService;
    
	public AdoptionController(AdoptionService adoptionService,PetService petService) {
		this.petService = petService;
		this.adoptionService = adoptionService;
	}
    
	@GetMapping(value = {"/adoptions"})
	public String showAdoptionList(final ModelMap model) {
		model.addAttribute("adoption", this.adoptionService.findAll());
		return "adoptions/adoptionsList";
	}
	
	@GetMapping(value="/adoptions/{adoptionId}/adoption")
	public String anyadirDescripcion(@Valid Adoption adoption, BindingResult result,@PathVariable("adoptionId") int adoptionId, ModelMap model) {
		Adoption petSeraAdoptada =this.adoptionService.findAdoptionById(adoptionId);
		model.put("adoptionToEdit", petSeraAdoptada);
		return VIEWS_DESCRIPTION ;
	}
    
    @PostMapping(value="/adoptions/{adoptionId}/adoption")
	public String solicitudAdopcion(@Valid Adoption adoption, BindingResult result,@PathVariable("adoptionId") int adoptionId, ModelMap model) {
    	if (result.hasErrors()) {
			model.put("adoptionToEdit", adoption);
			return VIEWS_DESCRIPTION;
		}
		else {
        	Adoption petSeraAdoptada =this.adoptionService.findAdoptionById(adoptionId);
        	BeanUtils.copyProperties(adoption, petSeraAdoptada); 
            this.adoptionService.saveAdoption(petSeraAdoptada);                    

		}
    	return VIEWS_DESCRIPTION ;
    }
  
}
