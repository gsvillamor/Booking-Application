package com.example.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Image;
import com.example.demo.model.TravelPackage;
import com.example.demo.model.TravelService;
import com.example.demo.repository.TravelPackageRepository;

public class TravelPackageService {

	private TravelPackageRepository travelPackageRepository;

	public TravelPackageService(TravelPackageRepository travelPackageRepository) {
		super();
		this.travelPackageRepository = travelPackageRepository;
	}

	@Transactional
	public List<TravelPackage> findAll() {
		return (List<TravelPackage>) travelPackageRepository.findAll();
	}

	@Transactional
	public TravelPackage findById(int travelPackageId) {
		return travelPackageRepository.findById(travelPackageId).get();
	}

	@Transactional
	public TravelPackage save(TravelPackage travelPackage) {
		return travelPackageRepository.save(travelPackage);
	}

	@Transactional
	public void deleteAllCustomers() {
		travelPackageRepository.deleteAll();
	}

	@Transactional
	public void delete(TravelPackage customer) {
		travelPackageRepository.delete(customer);
	}

	@Transactional
	public void deleteServices(ImageService imageService, ServiceService travelService, int travelPackageId) {
		// TODO Auto-generated method stub
		List<Image> images = imageService.findAll();
		for (Image img : images) {
			if (img.getTravelPackage().getTravelPackageId() == travelPackageId)
				imageService.delete(img);
		}

		List<TravelService> travelServices = (List<TravelService>) travelService.findAll();
		for (TravelService service : travelServices) {
			if (service.getTravelPackage().getTravelPackageId() == travelPackageId)
				travelService.delete(service);
		}
	}

}
