package com.example.demo.service;

import javax.transaction.Transactional;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Transactional
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Transactional
	public Customer findById(int customerId) {
		return customerRepository.findById(customerId).get();
	}

	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Transactional
	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}

}
