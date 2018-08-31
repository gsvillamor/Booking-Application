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

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@GetMapping
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerService.findAll();
	}

	@PutMapping
	public Customer editCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@DeleteMapping
	public void deleteAllCustomers(@RequestParam("customerId") List<Integer> customerIds) {
		for (int id : customerIds) {
			customerService.delete(customerService.findById(id));
		}
	}

	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") int customerId) {
		return customerService.findById(customerId);
	}

	@PutMapping("/{customerId}")
	public Customer editCustomerDetails(@PathVariable("customerId") int customerId, @RequestBody Customer customer) {
		customer.setCustomerId(customerId);
		return customerService.save(customer);
	}

	@DeleteMapping("/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") int customerId) {
		customerService.delete(customerService.findById(customerId));
	}
}
