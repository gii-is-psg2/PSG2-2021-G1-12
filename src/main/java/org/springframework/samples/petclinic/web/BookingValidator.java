package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookingValidator implements Validator {

	@Override
	public void validate(Object obj, Errors errors) {
		Booking booking = (Booking) obj;
		LocalDate start = booking.getStartDate();
		LocalDate finish = booking.getFinishDate();

		Pet pet = booking.getPet();
		Set<Booking> bookings = pet.getBookings();

		if(start == null) {
			errors.rejectValue("startDate", "Por favor selecciona una fecha", "Por favor selecciona una fecha");
		}else if(finish == null) {
			errors.rejectValue("finishDate", "Por favor selecciona una fecha", "Por favor selecciona una fecha");
		}else {

			if(start.isBefore(LocalDate.now())) {
				errors.rejectValue("startDate", "La fecha de inicio debe de ser posterior a la de hoy", "La fecha de inicio debe de ser posterior a la de hoy");
			}

			if(finish.isBefore(start)) {
				errors.rejectValue("finishDate", "La fecha final debe de ser posterior a la fecha de inicio", "La fecha final debe de ser posterior a la fecha de inicio");
			}

			for(Booking b: bookings) {
				LocalDate bStart = b.getStartDate();
				LocalDate bFinish = b.getFinishDate();

				if((start.isAfter(bStart)&&start.isBefore(bFinish))||(start.isEqual(bStart))) {
					errors.rejectValue("startDate", "La fecha introducida coincide con una reserva existente", "La fecha introducida coincide con una reserva existente");
					break;
				}
				if((finish.isAfter(bStart)&&finish.isBefore(bFinish))||(finish.isEqual(bFinish))) {
					errors.rejectValue("finishDate", "La fecha introducida coincide con una reserva existente", "La fecha introducida coincide con una reserva existente");
					break;
				}
				if(start.isBefore(bStart)&&finish.isAfter(bFinish)) {
					errors.rejectValue("startDate", "La fecha introducida coincide con una reserva existente", "La fecha introducida coincide con una reserva existente");
					errors.rejectValue("finishDate", "La fecha introducida coincide con una reserva existente", "La fecha introducida coincide con una reserva existente");
					break;
				}
			}

		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Booking.class.isAssignableFrom(clazz);
	}
}
