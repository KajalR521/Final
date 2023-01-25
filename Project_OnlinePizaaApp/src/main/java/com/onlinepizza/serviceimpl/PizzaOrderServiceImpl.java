package com.onlinepizza.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinepizza.dao.CouponRepository;
import com.onlinepizza.dao.PizzaOrderRepository;
import com.onlinepizza.dao.PizzaRepo;
import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.dto.PizzaOrderDTO;
import com.onlinepizza.entity.Coupon;
import com.onlinepizza.entity.OrderQuantity;
import com.onlinepizza.entity.Pizza;
import com.onlinepizza.entity.PizzaOrder;
import com.onlinepizza.exception.InvalidSizeException;
import com.onlinepizza.exception.OrderIdNotFoundException;
import com.onlinepizza.exception.PizzaIdNotFoundException;
import com.onlinepizza.service.PizzaOrderService;

@Service
public class PizzaOrderServiceImpl implements PizzaOrderService {
	@Autowired
	private PizzaOrderRepository pizzaorderrepo;
	@Autowired
	private CouponRepository cRepo;
	@Autowired
	private PizzaRepo pRepo;
	@Autowired
	private ModelMapper mapper;
	int i=0;

	@Override
	@Transactional
	public PizzaOrderDTO bookPizzaOrder(PizzaOrderDTO orderDto) {
		PizzaOrder pobj = mapper.map(orderDto, PizzaOrder.class);
		List<OrderQuantity> list=orderDto.getPizza().stream().map((p)->{
			OrderQuantity quantity=new OrderQuantity();
			Pizza pizza=new Pizza();
			pizza.setPizzaId(p);
			quantity.setPizza(pizza);
			quantity.setQuantity(orderDto.getQuantity().get(i++));
			return quantity;
		}).collect(Collectors.toList());
		System.out.println(pobj);  
		//pobj.setQuantity(list);
		 //findbycouponid 
		Coupon c=cRepo.findById(orderDto.getCoupon()).get();
		double price=0;
		List<Pizza> p=pRepo.findPizza(orderDto.getPizza());
		i=0;
		for(Pizza pizza:p) {
			price+=pizza.getPizzaCost()*orderDto.getQuantity().get(i++);
		}
		switch(c.getCouponType()) {
		case "gold":
			price=price-(price*0.2);
			break;
			
		case "silver":
			price=price-(price*0.1);
			break;
		}
		PizzaOrder po=pizzaorderrepo.save(pobj);
		PizzaOrderDTO pobj1 = mapper.map(po, PizzaOrderDTO.class);
		return  pobj1;
	}

	
	@Override
	public PizzaOrderDTO updatePizzaOrder(PizzaOrderDTO orderDto, int bookingOrderId) throws OrderIdNotFoundException {
			
			PizzaOrderDTO findPizza = viewPizzaOrder(bookingOrderId);
			//findPizza.setQuantity(orderDto.getQuantity());
			findPizza.setSize(orderDto.getSize());
			findPizza.setTotalCost(orderDto.getTotalCost());
			findPizza.setTransactionMode(orderDto.getTransactionMode());
			PizzaOrderDTO pobj1 = mapper.map(findPizza, PizzaOrderDTO.class);
			PizzaOrder pobj2 = mapper.map(pobj1, PizzaOrder.class);
			PizzaOrder po= pizzaorderrepo.save(pobj2);
			PizzaOrderDTO pobj3 = mapper.map(po, PizzaOrderDTO.class);
			return pobj3;
			
		}
	

	@Override
	public PizzaOrderDTO cancelPizzaOrder(int orderId) throws OrderIdNotFoundException {
		//Optional<PizzaOrderDTO> pFind = pizzaorderrepo.findById(orderId);
		Optional<PizzaOrder> po= pizzaorderrepo.findById(orderId);
	//	PizzaOrderDTO pDto = mapper.map(po.get(), PizzaOrderDTO.class);
		PizzaOrderDTO pizza = null;
		if (po.isPresent()) {
			pizza = mapper.map(po.get(), PizzaOrderDTO.class);
			pizzaorderrepo.deleteById(orderId);
		} else {
			throw new OrderIdNotFoundException("Order Id Not Found");
		}
		return pizza;
	}

	@Override
	public PizzaOrderDTO viewPizzaOrder(int orderId) throws OrderIdNotFoundException {
		Optional<PizzaOrder> pFind = pizzaorderrepo.findById(orderId);
		PizzaOrderDTO pobj = mapper.map(pFind, PizzaOrderDTO.class);
		if (pFind.isPresent()) {
			PizzaOrder pobj1 = mapper.map(pobj, PizzaOrder.class);
			PizzaOrderDTO pobj2 = mapper.map(pobj1, PizzaOrderDTO.class);
			return pobj2;
		} else {
			throw new OrderIdNotFoundException("Order Id Not Found");
		}
	}

	@Override
	public List<PizzaOrderDTO> caluculateTotal(String size, int quantity) throws InvalidSizeException {
		if (size.equals(null))
			throw new InvalidSizeException("Enter Proper Size");
		List<PizzaOrder> pobj1 =pizzaorderrepo.caluculateTotal(size, quantity);
		//PizzaOrderDTO pobj2 = mapper.map(pobj1, PizzaOrderDTO.class);
		List<PizzaOrderDTO>pobj3=new ArrayList<PizzaOrderDTO>();
		//pobj3=(List<PizzaOrderDTO>) pobj2;
		pobj3 = mapper.map(pobj1, new TypeToken<List<PizzaOrderDTO>>() {}.getType());
		return pobj3;
	}

}
