package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService {

	private DonationRepository donationRepository;
	
	@Autowired
	public DonationService(DonationRepository donationRepository) {
		this.donationRepository = donationRepository;
	}
	
	@Transactional(readOnly = true)
	public List<Donation> findByCauseId(Integer id) throws DataAccessException{
		return donationRepository.findByCauseId(id);
	}
	
	@Transactional
	public void save(Donation donation) {
		donationRepository.save(donation);
	}
	
	@Transactional(readOnly = true)
	public Optional<Donation> findDonationById(Integer id) {
		return donationRepository.findById(id);
	}
}
