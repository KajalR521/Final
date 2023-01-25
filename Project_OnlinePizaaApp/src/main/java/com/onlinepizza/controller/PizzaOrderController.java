package com.onlinepizza.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.onlinepizza.dto.PizzaOrderDTO;
import com.onlinepizza.exception.InvalidSizeException;
import com.onlinepizza.exception.OrderIdNotFoundException;
import com.onlinepizza.service.PizzaOrderService;

@RestController
@RequestMapping("/order")
public class PizzaOrderController {
	@Autowired
	private PizzaOrderService pService;

	@PostMapping
	public ResponseEntity<PizzaOrderDTO> bookPizzaOrder(@Valid @RequestBody PizzaOrderDTO pDto) {
		return new ResponseEntity(pService.bookPizzaOrder(pDto), HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<PizzaOrderDTO> findPizzaOrderById(@PathVariable("id") int id)
			throws OrderIdNotFoundException {
		return new ResponseEntity(pService.viewPizzaOrder(id), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<PizzaOrderDTO> updatePizzaOrder(@RequestBody PizzaOrderDTO orderDto,
			@PathVariable("id") int id) throws OrderIdNotFoundException {
		return new ResponseEntity(pService.updatePizzaOrder(orderDto, id), HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/{pizzaId}")
	public ResponseEntity<PizzaOrderDTO> delete(@PathVariable("orderId") int orderId) throws OrderIdNotFoundException {
		return new ResponseEntity(pService.cancelPizzaOrder(orderId), HttpStatus.OK);
	}

	@GetMapping("/totalcost/{size}/{quantity}")
	public ResponseEntity<List<PizzaOrderDTO>> getTotalCost(@PathVariable("size") String size,
			@PathVariable("quantity") int quantity) throws InvalidSizeException {
		return new ResponseEntity(pService.caluculateTotal(size, quantity), HttpStatus.OK);
	}

}
