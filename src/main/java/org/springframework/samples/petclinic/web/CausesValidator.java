package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.model.Causes;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CausesValidator implements Validator {

	@Override
	public void validate(Object obj, Errors errors) {
		Causes causes = (Causes) obj;

		try {

			Integer.parseInt(causes.toString());
		}catch(Exception e) {
			errors.rejectValue("budgetTarget", "Por favor introduzca un numero", "Por favor introduzca un numero");

		}

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Booking.class.isAssignableFrom(clazz);
	}
}