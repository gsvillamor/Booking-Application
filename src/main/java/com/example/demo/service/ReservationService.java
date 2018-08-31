package com.example.demo.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;

public class ReservationService {

	private ReservationRepository reservationRepository;

	public ReservationService(ReservationRepository reservationRepository) {
		super();
		this.reservationRepository = reservationRepository;
	}

	@Transactional
	public Iterable<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Transactional
	public Reservation findById(int reservationId) {
		return reservationRepository.findById(reservationId).get();
	}

	@Transactional
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Transactional
	public void delete(Reservation reservation) {
		reservationRepository.delete(reservation);
	}

}
