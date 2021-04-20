package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Causes;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.CausesService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CausesController {

	
	private final CausesService causeService;
	private final DonationService donationService;
	private final UserService userService;
	private final OwnerService ownerService;
	
	@Autowired
	public CausesController(CausesService causeService, DonationService donationService, UserService userService, OwnerService ownerService) {
		this.causeService = causeService;
		this.donationService = donationService;
		this.userService = userService;
		this.ownerService = ownerService;
	}
	
	@InitBinder("cause")
	public void initGerenteBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new CausesValidator());
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
//		List<Donation> donations = this.donationService.findByCauseId(causeId);
		Set<Donation> donations = cause.getDonations();
		
		model.addAttribute("cause", cause);
		model.addAttribute("donations", donations);

		return "causes/causeDetail";
	}

	@GetMapping(value = "/causes/{causeId}/donate")
	public String initDonationForm(@PathVariable("causeId") int causeId, ModelMap model) {
		Donation donation = new Donation();
		model.addAttribute("donation", donation);
		model.addAttribute("causeId", causeId);
		return "causes/donationCreate";
	}
	
	@PostMapping(value = "/causes/{causeId}/donate")
	public String processDonationForm(@Valid Donation donation, BindingResult result, @PathVariable("causeId") int causeId, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("donation", donation);
			model.addAttribute("causeId", causeId);
			return "causes/donationCreate";
		}else {
			LocalDate donationDate = LocalDate.now();
			donation.setDate(donationDate);
			User user = this.userService.getUserSession();
			donation.setUser(user);
			this.donationService.save(donation);
			
			Causes cause = this.causeService.findById(causeId).get();
			cause.setBudgetAchieved(cause.getBudgetAchieved()+donation.getAmount());
			this.causeService.save(cause);
			
			return "redirect:/causes/{causeId}/details";
		}
	}
	
	@GetMapping(value = "/causes/create")
	public String initCreateCausesForm(ModelMap model) {
		
		model.put("causes", new Causes());
		return "causes/createCause";
	}
	
	@PostMapping(value = "/causes/create")
	public String finishCreateCausesForm(@Valid Causes cause,BindingResult result,ModelMap model) {
		
		if(result.hasErrors()) {
			System.out.println(result);
			
			return "causes/createCause";
		}
		
		
		User userSession = this.userService.getUserSession();
		
		
		cause.setUser(userSession);
		cause.setBudgetAchieved(0.0);
		cause.setActive(true);
		this.userService.saveUser(userSession);

		this.causeService.save(cause);
		
		return "redirect:/causes";
	}
	
	
}
