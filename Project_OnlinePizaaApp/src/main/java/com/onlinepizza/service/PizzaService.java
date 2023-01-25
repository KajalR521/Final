package com.onlinepizza.service;

import java.util.List;

import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.entity.Pizza;
import com.onlinepizza.exception.InvalidMinCostException;
import com.onlinepizza.exception.PizzaIdNotFoundException;


public interface PizzaService {

	public PizzaDTO addPizza(PizzaDTO pizzaDto);

	public PizzaDTO updatePizza(int pizzaId,PizzaDTO pizzaDto)throws PizzaIdNotFoundException;

	public PizzaDTO deletePizza(int pizzaId) throws PizzaIdNotFoundException;

	public PizzaDTO viewPizza(int pizzaId) throws PizzaIdNotFoundException;

	public List<PizzaDTO> viewPizzaList();

	public List<PizzaDTO> viewPizzaList(double minCost, double maxCost)throws InvalidMinCostException;
}
