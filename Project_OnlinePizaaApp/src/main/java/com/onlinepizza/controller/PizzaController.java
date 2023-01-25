package com.onlinepizza.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.entity.Pizza;
import com.onlinepizza.exception.InvalidMinCostException;
import com.onlinepizza.exception.PizzaIdNotFoundException;
import com.onlinepizza.service.PizzaService;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	

	@PostMapping
	public ResponseEntity<PizzaDTO> add(@Valid @RequestBody PizzaDTO pizzaDto) {
		return new ResponseEntity(pizzaService.addPizza(pizzaDto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PizzaDTO>> getAll(){
		return new ResponseEntity(pizzaService.viewPizzaList(),HttpStatus.OK);
	}
	
	@GetMapping("/{pizzaId}")
	public ResponseEntity<PizzaDTO> getById(@PathVariable("pizzaId") int pizzaId)throws PizzaIdNotFoundException{
		return new ResponseEntity(pizzaService.viewPizza(pizzaId),HttpStatus.OK);
	}
	
	@GetMapping("/betweencost/{minCost}/{maxCost}")
	public ResponseEntity<List<PizzaDTO>> getByCost(@PathVariable("minCost") double minCost,@PathVariable("maxCost") double maxCost)throws InvalidMinCostException{
		return new ResponseEntity(pizzaService.viewPizzaList(minCost,maxCost),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{pizzaId}")
	public ResponseEntity<PizzaDTO> update(@Valid @RequestBody PizzaDTO pizzaDto,@PathVariable("pizzaId") int pizzaId)throws PizzaIdNotFoundException{
		return new ResponseEntity(pizzaService.updatePizza(pizzaId, pizzaDto),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{pizzaId}")
	public ResponseEntity<PizzaDTO> delete(@PathVariable("pizzaId") int pizzaId)throws PizzaIdNotFoundException{
		return new ResponseEntity(pizzaService.deletePizza(pizzaId),HttpStatus.OK);
	}
	

}
