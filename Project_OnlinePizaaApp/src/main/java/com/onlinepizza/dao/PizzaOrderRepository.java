package com.onlinepizza.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlinepizza.dto.PizzaOrderDTO;
import com.onlinepizza.entity.PizzaOrder;
import com.onlinepizza.exception.InvalidSizeException;

@Repository
public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Integer>{

	@Query("select po from PizzaOrder po where po.size =:size and po.quantity =:quantity")
	List<PizzaOrder> caluculateTotal(String size, int quantity) throws InvalidSizeException;

}
