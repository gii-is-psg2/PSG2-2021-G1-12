package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.BookingService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("owners/{ownerId}/bookings")
public class BookingController {

	private static final String VIEWS_BOOKINGS_LIST = "bookings/bookingsList";
	private static final String VIEWS_BOOKINGS_CREATE_OR_UPDATE_FORM = "bookings/createOrUpdateBookingForm";

	@Autowired
	private BookingService bookingService;
	@Autowired
	private PetService petService;

	@InitBinder("booking")
	public void initGerenteBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new BookingValidator());
	}

	@GetMapping()
	public String bookingsList(@PathVariable("ownerId") int ownerId, ModelMap modelMap) {
		Iterable<Booking> allBookings = bookingService.findAll();
		List<Booking> bookings = new ArrayList<>();
		for(Booking booking: allBookings) {
			if(booking.getPet().getOwner().getId() == ownerId)
				bookings.add(booking);
		}
		modelMap.addAttribute("bookings", bookings);
		return VIEWS_BOOKINGS_LIST;
	}

	@GetMapping(value = "/{petId}/new")
	public String initCreationForm(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, ModelMap modelMap) {
		Booking booking = new Booking();
		Pet pet = petService.findPetById(petId);
		modelMap.addAttribute("booking", booking);
		modelMap.addAttribute("ownerId", ownerId);
		modelMap.addAttribute(pet);
		return VIEWS_BOOKINGS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/{petId}/new")
	public String inicCreationForm(@Valid Booking booking, BindingResult result, @PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId, ModelMap modelMap) {
		if (result.hasErrors()) {
			Pet pet = petService.findPetById(petId);
			modelMap.addAttribute("booking", booking);
			modelMap.addAttribute("ownerId", ownerId);
			modelMap.addAttribute(pet);
			return VIEWS_BOOKINGS_CREATE_OR_UPDATE_FORM;
		}else {
			this.bookingService.save(booking);
			return "redirect:/owners/{ownerId}/bookings";
		}

	}
	
	@GetMapping(value = "/{bookingId}/delete")
	public String deleteBooking(@PathVariable("bookingId") int bookingId, ModelMap modelMap) {
		Optional<Booking> booking = bookingService.findBookingById(bookingId);
		if(booking.isPresent()) {
			bookingService.delete(booking.get());
			return "redirect:/owners/{ownerId}/bookings";
		}else {
			return "redirect:/owners/{ownerId}";
		}
	}
}
