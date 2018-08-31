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

import com.example.demo.model.TravelPackage;
import com.example.demo.service.ImageService;
import com.example.demo.service.ServiceService;
import com.example.demo.service.TravelPackageService;

@RestController
@RequestMapping("/travel-packages")
public class TravelPackageController {

	private TravelPackageService travelPackageService;
	private ImageService imageService;
	private ServiceService travelService;

	public TravelPackageController(TravelPackageService travelPackageService, ImageService imageService,
			ServiceService travelService) {
		super();
		this.travelPackageService = travelPackageService;
		this.imageService = imageService;
		this.travelService = travelService;
	}

	@GetMapping
	public List<TravelPackage> getAllTravelPackages() {
		return (List<TravelPackage>) travelPackageService.findAll();
	}

	@PutMapping
	public TravelPackage editTravelPackage(@RequestBody TravelPackage travelPackage) {
		return travelPackageService.save(travelPackage);
	}

	@PostMapping
	public TravelPackage addTravelPackage(@RequestBody TravelPackage travelPackage) {
		travelPackage = travelPackageService.save(travelPackage);

		travelService.setContents(travelPackage, travelService, imageService);

		return travelPackage;
	}

	@DeleteMapping
	public void deleteTravelPackages(@RequestParam("travelPackageId") List<Integer> travelPackageIds) {
		for (int id : travelPackageIds) {
			travelPackageService.deleteServices(imageService, travelService, id);
			travelPackageService.delete(travelPackageService.findById(id));
		}
	}

	@GetMapping("/{travelPackageId}")
	public TravelPackage getTravelPackageById(@PathVariable("travelPackageId") int travelPackageId) {
		return travelPackageService.findById(travelPackageId);
	}

	@PutMapping("/{travelPackageId}")
	public TravelPackage editTravelPackageDetails(@PathVariable("travelPackageId") int travelPackageId,
			@RequestBody TravelPackage travelPackage) {
		travelPackage.setTravelPackageId(travelPackageId);
		return travelPackageService.save(travelPackage);
	}

	@DeleteMapping("/{travelPackageId}")
	public void deleteTravelPackage(@PathVariable("travelPackageId") int travelPackageId) {
		travelPackageService.deleteServices(imageService, travelService, travelPackageId);
		travelPackageService.delete(travelPackageService.findById(travelPackageId));
	}
}
