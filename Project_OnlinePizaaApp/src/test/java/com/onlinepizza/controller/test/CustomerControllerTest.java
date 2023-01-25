package com.onlinepizza.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onlinepizza.controller.CustomerController;
import com.onlinepizza.dto.CustomerDTO;
import com.onlinepizza.entity.Customer;
import com.onlinepizza.exception.CustomerIdNotFoundException;
import com.onlinepizza.service.CustomerService;



@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

	@Mock
	private CustomerService cusService;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private CustomerController cusController;
	
	private Customer cus;
	private CustomerDTO cusDto;
	
	private List<CustomerDTO> customers;
	
	@BeforeEach
	void setUp() throws Exception{
		
		cusDto = new CustomerDTO();
		cusDto.setcId(1);
		cusDto.setcName("kiyya");
		cusDto.setcMobileNo(1234567890);
		cusDto.setcAddress("abc");
		cusDto.setcEmail("kiya@gmail.com");
		
	}
	
	@Test
	void testAddCustomer() {
		when(cusService.addCustomer(cusDto)).thenReturn(cusDto);
		ResponseEntity<CustomerDTO> response=cusController.createCustomer(cusDto);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}
	
	@Test
	void testByCustomer() throws CustomerIdNotFoundException {
		when(cusService.viewCustomer(1)).thenReturn(cusDto);
		ResponseEntity<CustomerDTO> response=cusController.viewCustomer(1);
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testDeleteById() throws CustomerIdNotFoundException {
		when(cusService.deleteCustomer(1)).thenReturn(cusDto);
		ResponseEntity<String> response=cusController.deletecustomer(1);
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testUpdate() throws CustomerIdNotFoundException {
		when(cusService.updateCustomer(cusDto, 1)).thenReturn(cusDto);
		ResponseEntity<CustomerDTO> response=cusController.updateCustomer(1, cusDto);
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
	}
	
	@Test
	void testViewCoustomers() throws CustomerIdNotFoundException{
		when(cusService.viewCustomers()).thenReturn(customers);
		ResponseEntity<List<CustomerDTO>> response=cusController.viewCustomers();
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
}
