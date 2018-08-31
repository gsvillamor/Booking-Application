package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "reservationId")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservationId;
	@ManyToMany(mappedBy = "reservation")
	private List<TravelService> availedServiceList;
	private LocalDate departureDate;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public List<TravelService> getAvailedServiceList() {
		return availedServiceList;
	}

	public void setAvailedServiceList(List<TravelService> availedServiceList) {
		this.availedServiceList = availedServiceList;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
