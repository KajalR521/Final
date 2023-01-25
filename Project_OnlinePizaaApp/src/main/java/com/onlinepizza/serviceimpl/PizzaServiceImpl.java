package com.onlinepizza.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.onlinepizza.dao.PizzaRepo;
import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.dto.CustomerDTO;
import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.dto.PizzaOrderDTO;
import com.onlinepizza.entity.Coupon;
import com.onlinepizza.entity.Customer;
import com.onlinepizza.entity.Pizza;
import com.onlinepizza.entity.PizzaOrder;
import com.onlinepizza.exception.InvalidMinCostException;
import com.onlinepizza.exception.PizzaIdNotFoundException;
import com.onlinepizza.service.PizzaService;

@Service
public class PizzaServiceImpl implements PizzaService{

	@Autowired
	private PizzaRepo pizzaRepo;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	@Transactional
	public PizzaDTO addPizza(PizzaDTO pizzaDto) {
		Pizza pobj = mapper.map(pizzaDto, Pizza.class);
		Pizza po=pizzaRepo.save(pobj);
		PizzaDTO pobj1 = mapper.map(po, PizzaDTO.class);
		return pobj1;
	}

	@Override
	public PizzaDTO updatePizza(int pizzaId,PizzaDTO pizzaDto) throws PizzaIdNotFoundException{
	
		PizzaDTO findPizza = viewPizza(pizzaId);
		findPizza.setPizzaName(pizzaDto.getPizzaName());
		findPizza.setPizzaType(pizzaDto.getPizzaType());
		findPizza.setPizzaDescription(pizzaDto.getPizzaDescription());
		findPizza.setPizzaCost(pizzaDto.getPizzaCost());
		//findPizza.setPizzaCostAfterCoupan(pizzaDto.getPizzaCostAfterCoupan());
		PizzaDTO pobj1 = mapper.map(findPizza, PizzaDTO.class);
		Pizza pobj2 = mapper.map(pobj1, Pizza.class);
		Pizza po= pizzaRepo.save(pobj2);
		PizzaDTO pobj3 = mapper.map(po, PizzaDTO.class);
		return pobj3;
	
	}

	@Override
	public PizzaDTO deletePizza(int pizzaId) throws PizzaIdNotFoundException {
			Optional<Pizza> po= pizzaRepo.findById(pizzaId);
			PizzaDTO p = null;
			if (po.isPresent()) {
				p = mapper.map(po.get(), PizzaDTO.class);
				pizzaRepo.deleteById(pizzaId);
		}
		else {
			throw new PizzaIdNotFoundException("Pizza Not Found with id "+pizzaId);
		}
		return p;
	}

	@Override
	public PizzaDTO viewPizza(int pizzaId) throws PizzaIdNotFoundException {
		
		Optional<Pizza> pFind = pizzaRepo.findById(pizzaId);
		PizzaDTO pobj = mapper.map(pFind, PizzaDTO.class);
		if (pFind.isPresent()) {
			Pizza pobj1 = mapper.map(pobj, Pizza.class);
			PizzaDTO pobj2 = mapper.map(pobj1, PizzaDTO.class);
			return pobj2;
		}
		else
			throw new PizzaIdNotFoundException("Pizza Not Found with id "+pizzaId);
	}

	@Override
	public List<PizzaDTO> viewPizzaList() {
		
		List<Pizza> p=pizzaRepo.findAll();
		PizzaDTO pobj2 = mapper.map(p, PizzaDTO.class);
		//List<PizzaDTO> po=(List<PizzaDTO>) pobj2;
		List<PizzaDTO> po = mapper.map(pobj2, new TypeToken<List<PizzaDTO>>() {}.getType());
		return po;
	}

	@Override
	public List<PizzaDTO> viewPizzaList(double minCost, double maxCost) throws InvalidMinCostException {
		if(minCost<0)
			throw new InvalidMinCostException("Invalid cost");		
		List<Pizza> pobj1 =pizzaRepo.viewPizzaList(minCost, maxCost);
		//PizzaDTO pobj2 = mapper.map(pobj1, PizzaDTO.class);
		//List<PizzaDTO>pobj3=new ArrayList<PizzaDTO>();
//		pobj3=(List<PizzaDTO>) pobj2;
		List<PizzaDTO> pobj3 = mapper.map(pobj1, new TypeToken<List<PizzaDTO>>() {}.getType());
		return pobj3;
	}

	

}
