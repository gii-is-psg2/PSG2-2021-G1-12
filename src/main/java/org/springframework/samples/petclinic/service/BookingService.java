package org.springframework.samples.petclinic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Booking;
import org.springframework.samples.petclinic.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

	private BookingRepository bookingRepository;
	
	@Autowired
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	@Transactional(readOnly = true)
	public Iterable<Booking> findAll() throws DataAccessException {
		return bookingRepository.findAll();
	}

	@Transactional
	public void save(Booking booking) {
		bookingRepository.save(booking);
	}
	
	@Transactional(readOnly = true)
	public Optional<Booking> findBookingById(int id) {
		return bookingRepository.findById(id);
	}
	
	@Transactional
	public void delete(Booking booking) {
		bookingRepository.delete(booking);
	}
}
