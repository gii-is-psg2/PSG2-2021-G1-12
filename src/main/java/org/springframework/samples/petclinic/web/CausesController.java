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
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CausesController {

	
	private final CausesService causeService;
	private final DonationService donationService;
	private final UserService userService;
	
	@Autowired
	public CausesController(CausesService causeService, DonationService donationService, UserService userService) {
		this.causeService = causeService;
		this.donationService = donationService;
		this.userService = userService;
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
			double budgetAchieved = cause.getBudgetAchieved()+donation.getAmount();
			Double budgetTarget = cause.getBudgetTarget();
			Double diff = (budgetAchieved-budgetTarget);
			
			if(budgetAchieved > budgetTarget) {
				String message = "Su donación sobrepasa la cantidad objetivo de la causa. Se le ha devuelto la diferencia ("+String.format("%.2f", diff)+") en el importe";
				model.addAttribute("message",message);
				budgetAchieved = budgetTarget;
			}
			cause.setBudgetAchieved(budgetAchieved);
			this.causeService.save(cause);
			
			Set<Donation> donations = cause.getDonations();
			model.addAttribute("cause", cause);
			model.addAttribute("donations", donations);
			return "causes/causeDetail";
//			return "redirect:/causes/{causeId}/details";
		}
	}
	
	@GetMapping(value = "/causes/create")
	public String initCreateCausesForm(ModelMap model) {
		
		model.put("causes", new Causes());
		return "causes/createCause";
	}
	
	@PostMapping(value = "/causes/create")
	public String finishCreateCausesForm(@Valid Causes nuevaCausa,BindingResult result,ModelMap model) {
		
		if(result.hasErrors()) {
			
			model.put("causes", nuevaCausa);
			
			return "causes/createCause";
		}
		
		
		User userSession = this.userService.getUserSession();
		
		
		nuevaCausa.setUser(userSession);
		nuevaCausa.setBudgetAchieved(0.0);
		nuevaCausa.setActive(true);
		this.userService.saveUser(userSession);

		this.causeService.save(nuevaCausa);
		
		return "redirect:/causes";
	}
	
	@GetMapping("/causes/{causeId}/details/delete")
	public String processDeleteCause(@PathVariable("causeId") int causeId) {
		this.causeService.deleteCauseById(causeId);
		return "redirect:/causes";
	}
	
	
	
	
}
