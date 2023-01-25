package com.onlinepizza.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinepizza.dto.CustomerDTO;
import com.onlinepizza.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
