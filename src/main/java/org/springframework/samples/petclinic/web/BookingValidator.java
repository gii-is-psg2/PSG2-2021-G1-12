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
			errors.rejectValue("startDate", "Por favor selecciona una fecha", "Por favor selecciona una fecha");
		}

		if(finish == null) {
			errors.rejectValue("finishDate", "Por favor selecciona una fecha", "Por favor selecciona una fecha");
		}else {

			if(start.isBefore(LocalDate.now())) {
				errors.rejectValue("startDate", "La fecha de inicio debe de ser posterior a la de hoy", "La fecha de inicio debe de ser posterior a la de hoy");
			}

			if(finish.isBefore(start)) {
				errors.rejectValue("finishDate", "La fecha final debe de ser posterior a la fecha de inicio", "La fecha final debe de ser posterior a la fecha de inicio");
			}
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Booking.class.isAssignableFrom(clazz);
	}
}
