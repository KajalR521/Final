package com.onlinepizza.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onlinepizza.controller.PizzaController;
import com.onlinepizza.controller.PizzaOrderController;
import com.onlinepizza.dto.PizzaOrderDTO;
import com.onlinepizza.entity.PizzaOrder;
import com.onlinepizza.exception.InvalidSizeException;
import com.onlinepizza.exception.OrderIdNotFoundException;
import com.onlinepizza.service.PizzaOrderService;

@ExtendWith(MockitoExtension.class)
public class PizzaOrderControllerTest {

	@Mock
	private PizzaOrderService pcService;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private PizzaOrderController pCont;
	
	private PizzaOrder pOrder;
	private PizzaOrderDTO poDto;
	
	private List<PizzaOrderDTO> pOrders;
	
	@BeforeEach
	void setUp() throws Exception{
		
		poDto = new PizzaOrderDTO();
		poDto.setBookingOrderId(11);
		//poDto.setQuantity(2);
		poDto.setSize("small");
		poDto.setTransactionMode("online");
		poDto.setTotalCost(200);
	}
	@Test
	void testBook() {
		when(pcService.bookPizzaOrder(poDto)).thenReturn(poDto);
		ResponseEntity<PizzaOrderDTO> response=pCont.bookPizzaOrder(poDto);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}
	
	@Test
	void testFindById() throws OrderIdNotFoundException  {
		when(pcService.viewPizzaOrder(11)).thenReturn(poDto);
		ResponseEntity<PizzaOrderDTO> response=pCont.findPizzaOrderById(11);
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testdeleteById() throws OrderIdNotFoundException  {
		when(pcService.cancelPizzaOrder(11)).thenReturn(poDto);
		ResponseEntity<PizzaOrderDTO> response=pCont.delete(11);
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testGetTotalCost() throws OrderIdNotFoundException, InvalidSizeException  {
		when(pcService.caluculateTotal("small", 200)).thenReturn(pOrders);
		ResponseEntity<List<PizzaOrderDTO>> response=pCont.getTotalCost("small", 200);
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testUpdate() throws OrderIdNotFoundException  {
		when(pcService.updatePizzaOrder(poDto, 11)).thenReturn(poDto);
		ResponseEntity<PizzaOrderDTO> response=pCont.updatePizzaOrder(poDto, 11);
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
	}
}
