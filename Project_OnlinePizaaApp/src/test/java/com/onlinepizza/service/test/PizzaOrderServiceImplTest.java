package com.onlinepizza.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlinepizza.dao.PizzaOrderRepository;
import com.onlinepizza.entity.PizzaOrder;
import com.onlinepizza.exception.OrderIdNotFoundException;
import com.onlinepizza.serviceimpl.PizzaOrderServiceImpl;

@ExtendWith(MockitoExtension.class)
class PizzaOrderServiceImplTest {
	@Mock
	private PizzaOrderRepository repo;

	@InjectMocks
	private PizzaOrderServiceImpl pService;
	private PizzaOrder pizza = null;

	private List<PizzaOrder> pizzas;

	@BeforeEach
	void setUp() throws Exception {

		pizza = new PizzaOrder();
		pizza.setBookingOrderId(11);
		pizza.setDateOfOrder(pizza.getDateOfOrder());
		// pizza.setQuantity(2);
		pizza.setSize("normal");
		pizza.setTotalCost(500);
		pizza.setTransactionMode("online");

		pizzas = new ArrayList<>();
		pizzas.add(pizza);
	}

//	@Test
//
//	void viewPizzaOrder() {
//		try {
//			Mockito.when(pService.viewPizzaOrder(11)).thenReturn(pizza);
//			assertEquals(pService.viewPizzaOrder(11), pizza);
//		} catch (OrderIdNotFoundException e) {
//			e.printStackTrace();
//		}
//
//	}

	@Test
	void cancelPizzaOrder() {
		try {
			pizzas.remove(0);
			Mockito.when(repo.findById(11)).thenReturn(Optional.of(pizza))
					.thenAnswer(i -> pService.cancelPizzaOrder(11));
			assertEquals(pizza, pService.cancelPizzaOrder(11));

		} catch (OrderIdNotFoundException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}
}
