package com.onlinepizza.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinepizza.dto.CustomerDTO;
import com.onlinepizza.entity.Customer;
import com.onlinepizza.exception.CustomerIdNotFoundException;
import com.onlinepizza.service.CustomerService;

@RestController
@RequestMapping("/cusomer")
public class CustomerController {

	@Autowired
	private CustomerService cService;

	// https://localhost:8080/Customer/Customer
	@GetMapping
	public ResponseEntity<List<CustomerDTO>> viewCustomers() {
		return new ResponseEntity(cService.viewCustomers(), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO cDto) {
		return new ResponseEntity(cService.addCustomer(cDto), HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> viewCustomer(@PathVariable("id") int id) throws CustomerIdNotFoundException {
		return new ResponseEntity(cService.viewCustomer(id), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerDTO cDto)
			throws CustomerIdNotFoundException {
		return new ResponseEntity(cService.updateCustomer(cDto, id), HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletecustomer(@PathVariable("id") int id) throws CustomerIdNotFoundException {
		return new ResponseEntity(cService.deleteCustomer(id), HttpStatus.OK);

	}
}
