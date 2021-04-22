package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.samples.petclinic.model.Adoption;
import org.springframework.samples.petclinic.model.AdoptionRequest;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdoptionRequestService;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdoptionController {
	private static final String VIEWS_DESCRIPTION = "adoptions/adoptionEdit";
	private static final String OWNERS_DETAILS="owners/ownerDetails";
	private static final String ADOPTION_LIST="adoptions/adoptionsList";

	
	private final PetService petService;
    private final AdoptionService adoptionService;
    private final OwnerService ownerService;
    private final AdoptionRequestService adoptionRequestService;
    private final UserService userService;
    
	public AdoptionController(AdoptionService adoptionService,PetService petService, OwnerService ownerService, AdoptionRequestService adoptionRequestService, UserService userService) {
		this.petService = petService;
		this.adoptionService = adoptionService;
		this.ownerService=ownerService;
		this.adoptionRequestService=adoptionRequestService;
		this.userService=userService;
	}
    
	@GetMapping(value = {"/adoptions"})
	public String showAdoptionList(final ModelMap model) {
		model.addAttribute("adoption", this.adoptionService.findAll());
		return "adoptions/adoptionsList";
	}
	
	@GetMapping(value="/adoptions/{adoptionId}/adoption")
	public String anyadirDescripcion(@Valid Adoption adoption, BindingResult result,@PathVariable("adoptionId") int adoptionId, ModelMap model) {
		Pet petSeraAdoptada =this.adoptionService.findAdoptionById(adoptionId).getPet();
        final AdoptionRequest adopreq= new AdoptionRequest();
        User user = userService.getUserSession();
        Owner owner = ownerService.findOwnerByUser(user.getUsername());
        adopreq.setOwner(owner);
        model.addAttribute("adoptionToEdit",adopreq);
        model.addAttribute("pet", petSeraAdoptada);
		return VIEWS_DESCRIPTION ;
	}
    
    @PostMapping(value="/adoptions/{adoptionId}/adoption")
	public String solicitudAdopcion(@Valid AdoptionRequest adoptionRequest, BindingResult result,@PathVariable("adoptionId") int adoptionId, ModelMap model) {
    	if (result.hasErrors()) {
			model.put("adoptionToEdit", adoptionRequest);
			return VIEWS_DESCRIPTION;
		}
		else {
        	model.addAttribute(adoptionRequest);
        	this.adoptionRequestService.saveAdoptionRequest(adoptionRequest);
		}
    	return VIEWS_DESCRIPTION ;
    }
    
    @GetMapping(value="/owners/{ownerId}/pets/{petId}/adoptions/new")
    public String newAdoption(@Valid Adoption adoption, BindingResult result, @PathVariable("petId") int petId, @PathVariable("ownerId") int ownerId, ModelMap model) {
		
    	if(result.hasErrors()) {
    		model.put("adoption", adoption);
    		return OWNERS_DETAILS;
    	}else {
    		Adoption adoption2 =new Adoption();
    		Owner owner = ownerService.findOwnerById(ownerId);
    		Pet pet = petService.findPetById(petId);
    		adoption2.setOwner(owner);
    		adoption2.setPet(pet);
    		adoptionService.saveAdoption(adoption2);
    		
    		return "redirect:/adoptions";
    	}
	}
   
}
