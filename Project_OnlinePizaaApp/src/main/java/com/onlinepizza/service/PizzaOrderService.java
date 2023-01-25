package com.onlinepizza.service;


import java.util.List;

import com.onlinepizza.dto.PizzaOrderDTO;
import com.onlinepizza.entity.PizzaOrder;
import com.onlinepizza.exception.InvalidSizeException;
import com.onlinepizza.exception.OrderIdNotFoundException;


public interface PizzaOrderService {
	PizzaOrderDTO bookPizzaOrder(PizzaOrderDTO orderDto);

	PizzaOrderDTO updatePizzaOrder(PizzaOrderDTO orderDto,int bookingOrderId)throws OrderIdNotFoundException;
	
	PizzaOrderDTO cancelPizzaOrder(int orderId) throws OrderIdNotFoundException;

	PizzaOrderDTO viewPizzaOrder(int orderId) throws OrderIdNotFoundException;

	List<PizzaOrderDTO> caluculateTotal(String size, int quantity) throws InvalidSizeException;

}
