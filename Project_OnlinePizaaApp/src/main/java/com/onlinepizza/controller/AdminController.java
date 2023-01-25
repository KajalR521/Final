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

import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.dto.CustomerDTO;
import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.dto.PizzaOrderDTO;
import com.onlinepizza.exception.CouponIdNotFoundException;
import com.onlinepizza.exception.CustomerIdNotFoundException;
import com.onlinepizza.exception.InvalidCouponOperationException;
import com.onlinepizza.exception.InvalidMinCostException;
import com.onlinepizza.exception.OrderIdNotFoundException;
import com.onlinepizza.exception.PizzaIdNotFoundException;
import com.onlinepizza.service.CouponService;
import com.onlinepizza.service.CustomerService;
import com.onlinepizza.service.PizzaOrderService;
import com.onlinepizza.service.PizzaService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CouponService coService;
	@Autowired
	private CustomerService cService;

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private PizzaOrderService pService;

	@PostMapping("/coupon")
	public ResponseEntity<CouponDTO> addCoupons(@Valid @RequestBody CouponDTO cDto) {
		return new ResponseEntity(coService.addCoupans(cDto), HttpStatus.CREATED);

	}

	@PutMapping("/coupon/{id}")
	public ResponseEntity<CouponDTO> editCoupon(@PathVariable("id") int id, @RequestBody CouponDTO couponDto)
			throws InvalidCouponOperationException {
		return new ResponseEntity(coService.editCoupans(couponDto, id), HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/coupon/{id}")
	public ResponseEntity<CouponDTO> deleteCoupon(@PathVariable("id") int id) throws CouponIdNotFoundException {
		return new ResponseEntity(coService.deleteCoupans(id), HttpStatus.OK);

	}

	@GetMapping("coupons")
	public ResponseEntity<List<CouponDTO>> viewCoupons() {
		return new ResponseEntity(coService.viewCoupons(), HttpStatus.OK);

	}
	@GetMapping("/customer")
	public ResponseEntity<List<CustomerDTO>> viewCustomers() {
		return new ResponseEntity(cService.viewCustomers(), HttpStatus.OK);

	}

	@PostMapping("/customer")
	public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO cDto) {
		return new ResponseEntity(cService.addCustomer(cDto), HttpStatus.CREATED);

	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerDTO> viewCustomer(@PathVariable("id") int id) throws CustomerIdNotFoundException {
		return new ResponseEntity(cService.viewCustomer(id), HttpStatus.CREATED);

	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerDTO cDto)
			throws CustomerIdNotFoundException {
		return new ResponseEntity(cService.updateCustomer(cDto, id), HttpStatus.CREATED);

	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<String> deletecustomer(@PathVariable("id") int id) throws CustomerIdNotFoundException {
		return new ResponseEntity(cService.deleteCustomer(id), HttpStatus.CREATED);

	}

	@PostMapping("/pizza")
	public ResponseEntity<PizzaDTO> add(@Valid @RequestBody PizzaDTO pizzaDto) {
		return new ResponseEntity(pizzaService.addPizza(pizzaDto), HttpStatus.CREATED);
	}

	@GetMapping("/pizzas")
	public ResponseEntity<List<PizzaDTO>> getAll() {
		return new ResponseEntity(pizzaService.viewPizzaList(), HttpStatus.OK);
	}

	@GetMapping("/pizza/{pizzaId}")
	public ResponseEntity<PizzaDTO> getById(@PathVariable("pizzaId") int pizzaId) throws PizzaIdNotFoundException {
		return new ResponseEntity(pizzaService.viewPizza(pizzaId), HttpStatus.OK);
	}

	@GetMapping("/betweencost/{minCost}/{maxCost}")
	public ResponseEntity<List<PizzaDTO>> getByCost(@PathVariable("minCost") double minCost,
			@PathVariable("maxCost") double maxCost) throws InvalidMinCostException {
		return new ResponseEntity(pizzaService.viewPizzaList(minCost, maxCost), HttpStatus.ACCEPTED);
	}

	@PutMapping("/pizza/{pizzaId}")
	public ResponseEntity<PizzaDTO> update(@Valid @RequestBody PizzaDTO pizzaDto, @PathVariable("pizzaId") int pizzaId)
			throws PizzaIdNotFoundException {
		return new ResponseEntity(pizzaService.updatePizza(pizzaId, pizzaDto), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/pizza/{pizzaId}")
	public ResponseEntity<PizzaDTO> delete(@PathVariable("pizzaId") int pizzaId) throws PizzaIdNotFoundException {
		return new ResponseEntity(pizzaService.deletePizza(pizzaId), HttpStatus.OK);
	}

	@PostMapping("/pizzaorder")
	public ResponseEntity<PizzaOrderDTO> bookPizzaOrder(@Valid @RequestBody PizzaOrderDTO pDto) {
		return new ResponseEntity(pService.bookPizzaOrder(pDto), HttpStatus.CREATED);

	}

	@GetMapping("/pizzaorder/{id}")
	public ResponseEntity<PizzaOrderDTO> findPizzaOrderById(@PathVariable("id") int id)
			throws OrderIdNotFoundException {
		return new ResponseEntity(pService.viewPizzaOrder(id), HttpStatus.OK);

	}

	@PutMapping("/pizzaorder/{id}")
	public ResponseEntity<PizzaOrderDTO> updatePizzaOrder(@RequestBody PizzaOrderDTO orderDto,
			@PathVariable("id") int id) throws OrderIdNotFoundException {
		return new ResponseEntity(pService.updatePizzaOrder(orderDto, id), HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/{pizzaId}")
	public ResponseEntity<PizzaOrderDTO> deleteOrder(@PathVariable("orderId") int orderId)
			throws OrderIdNotFoundException {
		return new ResponseEntity(pService.cancelPizzaOrder(orderId), HttpStatus.OK);
	}
}
