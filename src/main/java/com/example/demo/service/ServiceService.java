package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.Image;
import com.example.demo.model.TravelPackage;
import com.example.demo.model.TravelService;
import com.example.demo.repository.ServiceRepository;

public class ServiceService {

	private ServiceRepository serviceRepository;

	public ServiceService(ServiceRepository serviceRepository) {
		super();
		this.serviceRepository = serviceRepository;
	}

	@Transactional
	public Iterable<TravelService> findAll() {
		return serviceRepository.findAll();
	}

	@Transactional
	public TravelService findById(int travelPackageId) {
		return serviceRepository.findById(travelPackageId).get();
	}

	@Transactional
	public TravelService save(TravelService travelService) {
		return serviceRepository.save(travelService);
	}

	@Transactional
	public void delete(TravelService travelService) {
		serviceRepository.delete(travelService);
	}

	@Transactional
	public void deleteServices(ImageService imageService, int serviceId) {
		// TODO Auto-generated method stub
		List<Image> images = imageService.findAll();
		for (Image img : images) {
			if (img.getTravelPackage().getTravelPackageId() == serviceId)
				imageService.delete(img);
		}
	}

	@Transactional
	public void setContents(TravelPackage travelPackage, ServiceService travelService, ImageService imageService) {
		// TODO Auto-generated method stub

		for (TravelService service : travelPackage.getAvailableServiceList()) {
			service.setTravelPackage(travelPackage);
			travelService.save(service);

			for (Image image : service.getImages()) {
				image.setTravelPackage(travelPackage);
				imageService.save(image);
			}
		}

		for (Image image : travelPackage.getImages()) {
			image.setTravelPackage(travelPackage);
			imageService.save(image);
		}
	}
}
