package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Booking;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookingValidator implements Validator {

	@Override
	public void validate(Object obj, Errors errors) {
		Booking booking = (Booking) obj;

		LocalDate start = booking.getStartDate();
		LocalDate finish = booking.getFinishDate();

		if(start == null) {
			errors.rejectValue("startDate", "You must select a valid date", "You must select a valid date");
		}

		if(finish == null) {
			errors.rejectValue("finishDate", "You must select a valid date", "You must select a valid date");
		}else {

			if(start.isBefore(LocalDate.now())) {
				errors.rejectValue("startDate", "The start date of the booking must be later than today's", "The start date of the booking must be later than today's");
			}

			if(finish.isBefore(start)) {
				errors.rejectValue("finishDate", "The finish date of the booking must be set later than the start date", "The finish date of the booking must be set later than the start date");
			}
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Booking.class.isAssignableFrom(clazz);
	}
}
