package com.example.demo.controller;

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

import com.example.demo.model.TravelService;
import com.example.demo.service.ImageService;
import com.example.demo.service.ServiceService;
import com.example.demo.service.TravelPackageService;

@RestController
@RequestMapping("/travel-packages/{travelPackageId}/services")
public class ServiceController {

	private ServiceService service;
	private TravelPackageService travelPackageService;
	private ImageService imageService;

	public ServiceController(ServiceService service, TravelPackageService travelPackageService,
			ImageService imageService) {
		super();
		this.service = service;
		this.travelPackageService = travelPackageService;
		this.imageService = imageService;
	}

	@GetMapping
	public List<TravelService> getAllServices(@PathVariable("travelPackageId") int travelPackageId) {
		return travelPackageService.findById(travelPackageId).getAvailableServiceList();
	}

	@PutMapping
	public TravelService editCustomer(@PathVariable("travelPackageId") int travelPackageId,
			@RequestBody TravelService travelService) {
		travelService.setTravelPackage(travelPackageService.findById(travelPackageId));
		return service.save(travelService);
	}

	@PostMapping
	public TravelService addService(@PathVariable("travelPackageId") int travelPackageId,
			@RequestBody TravelService travelService) {

		travelService = service.save(travelService);
		imageService.setImages(travelService, imageService, travelPackageService, travelPackageId);

		return travelService;
	}

	@DeleteMapping
	public void deleteService(@PathVariable("travelPackageId") int travelPackageId,
			@RequestParam("serviceId") List<Integer> serviceIds) {
		for (int id : serviceIds) {
			service.deleteServices(imageService, id);
			service.delete(service.findById(id));
		}
	}

	@GetMapping("/{serviceId}")
	public TravelService getServiceById(@PathVariable("serviceId") int serviceId) {
		return service.findById(serviceId);
	}

	@PutMapping("/{serviceId}")
	public TravelService editServiceDetails(@PathVariable("serviceId") int serviceId,
			@RequestBody TravelService travelService) {
		travelService.setTravelPackage(travelPackageService.findById(serviceId));
		return service.save(travelService);
	}

	@DeleteMapping("/{serviceId}")
	public void deleteService(@PathVariable("serviceId") int serviceId) {
		service.deleteServices(imageService, serviceId);
		service.delete(service.findById(serviceId));
	}
}
