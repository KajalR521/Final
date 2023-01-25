package com.onlinepizza.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onlinepizza.controller.PizzaController;
import com.onlinepizza.dao.PizzaRepo;
import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.entity.Pizza;
import com.onlinepizza.exception.InvalidMinCostException;
import com.onlinepizza.exception.InvalidPizzaEntryException;
import com.onlinepizza.exception.PizzaIdNotFoundException;
import com.onlinepizza.service.PizzaService;
import com.onlinepizza.serviceimpl.PizzaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PizzaControllerTest {
	
	@Mock
	private PizzaService pizzaService;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private PizzaController pController;
	
	
	private Pizza pizza;
	private PizzaDTO pDto;

	private List<PizzaDTO> pizzas;

	@BeforeEach
	void setUp() throws Exception {

		pDto = new PizzaDTO();
		pDto.setPizzaId(1);
		pDto.setPizzaType("normal");
		pDto.setPizzaName("corn");
		pDto.setPizzaDescription("spicy");
		pDto.setPizzaCost(100);
	//	pDto.setPizzaCostAfterCoupan(50);
		
		pizza=mapper.map(pDto,Pizza.class);

		pizzas = new ArrayList<>();
		pizzas.add(pDto);
	}

	@Test
	void testAddPizza() throws InvalidPizzaEntryException{
		when(pizzaService.addPizza(pDto)).thenReturn(pDto);
		ResponseEntity<PizzaDTO> response=pController.add(pDto);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}
	
	@Test
	void testdeletePizza() throws PizzaIdNotFoundException {
		when(pizzaService.deletePizza(1)).thenReturn(pDto);
		ResponseEntity<PizzaDTO> response=pController.delete(1);
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testGetById() throws PizzaIdNotFoundException  {
		when(pizzaService.viewPizza(1)).thenReturn(pDto);
		ResponseEntity<PizzaDTO> response=pController.getById(1);
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testGetAll() throws PizzaIdNotFoundException   {
		when(pizzaService.viewPizzaList()).thenReturn(pizzas);
		ResponseEntity<List<PizzaDTO>> response=pController.getAll();
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testUpdate() throws PizzaIdNotFoundException  {
		when(pizzaService.updatePizza(1, pDto)).thenReturn(pDto);
		ResponseEntity<PizzaDTO> response=pController.update(pDto, 1);
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
	}
	
	@Test
	void testByCost() throws InvalidMinCostException  {
		when(pizzaService.viewPizzaList(100, 300)).thenReturn(pizzas);
		ResponseEntity<List<PizzaDTO>> response=pController.getByCost(100, 300);
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
	}
	

}
