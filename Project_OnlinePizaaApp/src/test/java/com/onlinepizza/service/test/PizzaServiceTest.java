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

import com.onlinepizza.dao.PizzaRepo;
import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.entity.Pizza;
import com.onlinepizza.exception.InvalidMinCostException;
import com.onlinepizza.exception.PizzaIdNotFoundException;
import com.onlinepizza.serviceimpl.PizzaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceTest {

	@Mock
	private PizzaRepo pizzaRepo;

	@InjectMocks
	private PizzaServiceImpl pizzaService;

	private Pizza pizza;


	private List<Pizza> pizzas;

	@BeforeEach
	void setUp() throws Exception {

		pizza = new Pizza();
		pizza.setPizzaId(1);
		pizza.setPizzaType("normal");
		pizza.setPizzaName("corn");
		pizza.setPizzaDescription("spicy");
		pizza.setPizzaCost(100);
		pizza.setPizzaCostAfterCoupan(50);

		pizzas = new ArrayList<>();
		pizzas.add(pizza);
	}

	
//	@Test
//	void testViewPizzaList() {
//		when(pizzaService.viewPizzaList()).thenReturn(pizzas);
//		assertEquals(pizzas,pizzaService.viewPizzaList());
//	}
	@Test
	void testViewPizza() {
		try {
			when(pizzaRepo.findById(1)).thenReturn(Optional.of(pizza))
					.then(i -> pizzaService.viewPizza(1));
			assertEquals(pizza, pizzaService.viewPizza(1));
		} catch (PizzaIdNotFoundException e) {
			fail("Exception not expected");
		}
	}


	@Test
	void testDeletePizza() {
		try {
			pizzas.remove(0);
			Mockito.when(pizzaRepo.findById(1)).thenReturn(Optional.of(pizza))
					.thenAnswer(i -> pizzaService.deletePizza(1));
			assertEquals(pizza, pizzaService.deletePizza(1));
			assertEquals(pizzas, pizzaService.viewPizzaList());
		} catch (PizzaIdNotFoundException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}

	@Test
	void testViewPizzaListRange() {
		try {
			when(pizzaRepo.viewPizzaList(100, 300)).thenReturn(pizzas);
			assertEquals(pizzas, pizzaService.viewPizzaList(100, 300));
		} catch (InvalidMinCostException e) {
			e.getMessage();
			fail("caused exception");
		}
	}

}
