package com.onlinepizza.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.onlinepizza.dao.CustomerRepository;
import com.onlinepizza.entity.Customer;
import com.onlinepizza.exception.CustomerIdNotFoundException;
import com.onlinepizza.serviceimpl.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository cRepo;

	@InjectMocks
	private CustomerServiceImpl cService;

	private List<Customer> cList;
	private Customer cus = null;

	private CustomerServiceImplTest() {
		cService = new CustomerServiceImpl();
		cList = new ArrayList<>();
	}

	@BeforeEach
	private void initEach() {
		cus = new Customer();
		cus.setcId(504);
		cus.setcName("Joy");
		cus.setcEmail("joy@gmail.com");
		cus.setcMobileNo(43678056);
		cus.setcAddress("Andhra pradesh");

		cList.add(cus);

	}

//	@Test
//
//	public void viewCustomersTest() {
//		Mockito.when(cService.viewCustomers()).thenReturn(cList);
//		assertEquals(cList, cService.viewCustomers());
//
//	}


	@Test
	void viewCustomerByIdTest() {

//		try {
//			Customer isPresent = cService.viewCustomer(504);
//			assertEquals(cService.viewCustomer(504), isPresent);
//		} catch (CustomerIdNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("customers are Can't able to view");
//		}
	}



	@Test
	void deleteCustomerTest() {

		try {

			Mockito.when(cRepo.findById(504)).thenReturn(Optional.of(cus))
					.thenAnswer(i -> cService.deleteCustomer(504));
			assertEquals(cus, cService.deleteCustomer(504));
		} catch (CustomerIdNotFoundException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}

}
