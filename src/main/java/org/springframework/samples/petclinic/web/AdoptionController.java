package org.springframework.samples.petclinic.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	private static final String ADOPTION = "/adoptions";
	
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
		Adoption a=this.adoptionService.findAdoptionById(adoptionId);
		Pet petSeraAdoptada =this.adoptionService.findAdoptionById(adoptionId).getPet();
		AdoptionRequest adopreq= new AdoptionRequest();
        
        
        model.addAttribute("adoption", a);
        model.addAttribute("adoptionRequest",adopreq);
        model.addAttribute("pet", petSeraAdoptada);
		return VIEWS_DESCRIPTION ;
	}
    
	
    @PostMapping(value="/adoptions/{adoptionId}/adoption")
	public String solicitudAdopcion(@Valid AdoptionRequest adoptionRequest, BindingResult result,@PathVariable("adoptionId") int adoptionId, ModelMap model) {	
		Adoption a=this.adoptionService.findAdoptionById(adoptionId);
    	Pet petSeraAdoptada = a.getPet();
       
    	User user = userService.getUserSession();
        String u1=user.getUsername(); 
        Owner owner = ownerService.findOwnerByUserName(u1);
        adoptionRequest.setOwner(owner);
        //System.out.println(owner);
        adoptionRequest.setAdoption(a);
    	
    	if (result.hasErrors()) {
    		model.addAttribute("adoptionRequest",adoptionRequest);
            model.addAttribute("pet", petSeraAdoptada);
			return VIEWS_DESCRIPTION;
		}
		else {
        	this.adoptionRequestService.saveAdoptionRequest(adoptionRequest);
		}
    	return "redirect:"+ADOPTION ;
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
   

   @GetMapping(value="/owners/{ownerId}/pets/{petId}/adoptions/solicitudes")
    public String solicitudesAdopciones(@Valid Adoption adoption, BindingResult result, @PathVariable("petId") int petId, @PathVariable("ownerId") int ownerId, ModelMap model) {
	    List<AdoptionRequest> solicitudes= this.adoptionRequestService.findAdoptionRequestByIdPet(petId);
    	//List<AdoptionRequest> solicitudes= this.adoptionRequestService.findAdoptionRequestByAdoptionPetId(petId);
    	model.addAttribute("solicitudes",solicitudes);
    	return "adoptions/adoptionRequestList";
    	}
    
    @GetMapping(value="/adoptions/{adoptionId}/{adoptionRequestId}/aceptar")
	public String aceptarSolicitudAdopcion(@Valid Adoption adoption, BindingResult result,@PathVariable("adoptionId") int adoptionId, @PathVariable("adoptionRequestId") int adoptionRequestId, ModelMap model) {	
    	AdoptionRequest adopreq= this.adoptionRequestService.findAdoptionRequestById(adoptionRequestId);
    	Adoption adop = adopreq.getAdoption();
    	Pet mascota = adop.getPet();
    	Owner antiguoOwner = mascota.getOwner();
    	Owner nuevoOwner = adopreq.getOwner();
    	nuevoOwner.addPet(mascota);
    	antiguoOwner.removePet(mascota);
    	this.adoptionService.deleteAdoption(adop);	
    	return "redirect:"+ ADOPTION ;
    }
    
    @GetMapping(value="/adoptions/{adoptionId}/{adoptionRequestId}/denegar")
	public String denegarSolicitudAdopcion(@Valid Adoption adoption, BindingResult result,@PathVariable("adoptionId") int adoptionId, @PathVariable("adoptionRequestId") int adoptionRequestId, ModelMap model) {	
    	AdoptionRequest adopreq= this.adoptionRequestService.findAdoptionRequestById(adoptionRequestId);
    	this.adoptionRequestService.deleteAdoptionRequest(adopreq);
    	return "redirect:"+ "/owners/find" ;
    }
    
    
}
