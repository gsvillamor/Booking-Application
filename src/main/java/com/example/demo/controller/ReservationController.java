package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Reservation;
import com.example.demo.model.TravelService;
import com.example.demo.service.ImageService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.ServiceService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	private ReservationService reservationService;
	private ServiceService serviceService;
	private ImageService imageService;

	public ReservationController(ReservationService reservationService, ServiceService serviceService,
			ImageService imageService) {
		super();
		this.reservationService = reservationService;
		this.serviceService = serviceService;
		this.imageService = imageService;
	}

	@GetMapping
	public List<Reservation> getAllReservations() {
		return (List<Reservation>) reservationService.findAll();
	}

	@PutMapping
	public Reservation editReservation(@RequestBody Reservation reservation) {
		return reservationService.save(reservation);
	}

	@PostMapping
	public Reservation addReservation(@RequestBody Reservation reservation) {
		reservation = reservationService.save(reservation);
		List<TravelService> services = new ArrayList<TravelService>();

		for (TravelService travelService : reservation.getAvailedServiceList()) {
			services.add(travelService);
		}

		reservation.setAvailedServiceList(services);
		return reservation;

	}

	@DeleteMapping
	public void deleteReservations(@RequestParam("reservationId") List<Integer> reservationIds) {
		for (int id : reservationIds) {
			reservationService.delete(reservationService.findById(id));
		}
	}

	@GetMapping("/{reservationId}")
	public Reservation getReservationById(@PathVariable("reservationId") int reservationId) {
		return reservationService.findById(reservationId);
	}

	@PutMapping("/{reservationId}")
	public Reservation editReservationDetails(@PathVariable("reservationId") int reservationId,
			@RequestBody Reservation reservation) {
		reservation.setReservationId(reservationId);
		return reservationService.save(reservation);
	}

	@DeleteMapping("/{reservationId}")
	public void deleteTravelPackage(@PathVariable("reservationId") int travelPackageId) {
		reservationService.delete(reservationService.findById(travelPackageId));
	}

}
