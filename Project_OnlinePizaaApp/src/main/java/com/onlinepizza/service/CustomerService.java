package com.onlinepizza.service;

import java.util.List;

import com.onlinepizza.dto.CustomerDTO;
import com.onlinepizza.entity.Customer;
import com.onlinepizza.exception.CustomerIdNotFoundException;


public interface CustomerService {
	CustomerDTO addCustomer(CustomerDTO customerDto);

	CustomerDTO updateCustomer(CustomerDTO customerDto,int customerId)throws CustomerIdNotFoundException;

    CustomerDTO deleteCustomer(int customerId) throws CustomerIdNotFoundException;

	List<CustomerDTO> viewCustomers();

	CustomerDTO viewCustomer(int customerId) throws CustomerIdNotFoundException;

	

	

	

	

	


}
