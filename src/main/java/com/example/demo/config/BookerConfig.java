package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.TravelPackageRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ImageService;
import com.example.demo.service.ServiceService;
import com.example.demo.service.TravelPackageService;

@Configuration
public class BookerConfig {
	@Bean
	public CustomerService customerService(CustomerRepository customerRepository) {
		return new CustomerService(customerRepository);

	}

	@Bean
	public ImageService imageService(ImageRepository imageRepository) {
		return new ImageService(imageRepository);

	}

	@Bean
	public ServiceService service(ServiceRepository serviceRepository) {
		return new ServiceService(serviceRepository);

	}

	@Bean
	public TravelPackageService travelPackageService(TravelPackageRepository travelPackageRepository) {
		return new TravelPackageService(travelPackageRepository);

	}

}
