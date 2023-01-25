package com.onlinepizza.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.onlinepizza.controller.CustomerController;
import com.onlinepizza.dao.CustomerRepository;
import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.dto.CustomerDTO;
import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.dto.PizzaOrderDTO;
import com.onlinepizza.entity.Coupon;
import com.onlinepizza.entity.Customer;
import com.onlinepizza.entity.Pizza;
import com.onlinepizza.entity.PizzaOrder;
import com.onlinepizza.exception.CustomerIdNotFoundException;
import com.onlinepizza.service.CustomerService;

@Component
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository cRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public CustomerDTO addCustomer(CustomerDTO customerDto) {
		Customer cobj = mapper.map(customerDto, Customer.class);
		Customer co = cRepo.save(cobj);
		CustomerDTO cobj1 = mapper.map(co, CustomerDTO.class);
		return cobj1;

	}

	@Override
	public List<CustomerDTO> viewCustomers() {
	List<Customer> cobj1 =cRepo.findAll();
		
		List<CustomerDTO> cList=new ArrayList<>();
		cList = mapper.map(cobj1, new TypeToken<List<CustomerDTO>>() {}.getType());
		System.out.println(cList);
		return cList;
	}

	@Override
	@Transactional
	public CustomerDTO viewCustomer(int customerid) throws CustomerIdNotFoundException {
		Optional<Customer> cFind = cRepo.findById(customerid);
		CustomerDTO cobj = mapper.map(cFind, CustomerDTO.class);
		if (cFind.isPresent()) {
			Customer cobj1 = mapper.map(cobj, Customer.class);
			CustomerDTO cobj2 = mapper.map(cobj1, CustomerDTO.class);
			return cobj2;
		}

		throw new CustomerIdNotFoundException("No found with id : " + customerid);
	}

	@Override
	@Transactional
	public CustomerDTO updateCustomer(CustomerDTO customerDto, int customerid) throws CustomerIdNotFoundException {
		CustomerDTO findCus = viewCustomer(customerid);
		findCus.setcAddress(customerDto.getcAddress());
		findCus.setcEmail(customerDto.getcEmail());
		findCus.setcId(customerDto.getcId());
		findCus.setcMobileNo(customerDto.getcMobileNo());
		CustomerDTO cobj1 = mapper.map(findCus, CustomerDTO.class);
		Customer cobj2 = mapper.map(cobj1, Customer.class);
		Customer co = cRepo.save(cobj2);
		return mapper.map(co, CustomerDTO.class);

	}

	@Override
	@Transactional
	public CustomerDTO deleteCustomer(int customerId) throws CustomerIdNotFoundException {
//		
		Optional<Customer> po= cRepo.findById(customerId);
			CustomerDTO cus = null;
			if (po.isPresent()) {
				cus = mapper.map(po.get(), CustomerDTO.class);
				cRepo.deleteById(customerId);
		} else {
			throw new CustomerIdNotFoundException("Customer not found");
		}
		return cus;
		
	}

}
