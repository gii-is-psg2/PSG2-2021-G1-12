package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causes;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.CausesService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CausesController {

	
	private final CausesService causeService;

	@Autowired
	public CausesController(CausesService causeService) {
		this.causeService = causeService;
	}
	
	
	@GetMapping(value = "/causes")
	public String causesListing(ModelMap model) {
		List<Causes> allCauses = this.causeService.findAll();
		model.addAttribute("listCauses", allCauses);
		return "causes/causes";
				
	}
	
	@GetMapping(value = "/causes/{causeId}/details")
	public String initUpdateOwnerForm(@PathVariable("causeId") int causeId, ModelMap model) {
		Causes cause = this.causeService.findById(causeId).get();
		
		model.addAttribute("cause", cause);

		return "causes/causeDetail";

		
	}


}
