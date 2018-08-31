package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.Image;
import com.example.demo.model.TravelService;
import com.example.demo.repository.ImageRepository;

public class ImageService {

	private ImageRepository imageRepository;

	public ImageService(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}

	@Transactional
	public List<Image> findAll() {
		return (List<Image>) imageRepository.findAll();
	}

	@Transactional
	public Image findById(int imgId) {
		return imageRepository.findById(imgId).get();
	}

	@Transactional
	public Image save(Image img) {
		return imageRepository.save(img);
	}

	@Transactional
	public void delete(Image image) {
		imageRepository.delete(image);
	}

	@Transactional
	public void setImages(TravelService travelService, ImageService imageService,
			TravelPackageService travelPackageService, int travelPackageId) {
		for (Image image : travelService.getImages()) {
			image.setTravelPackage(travelPackageService.findById(travelPackageId));
			imageService.save(image);
		}
	}

}
