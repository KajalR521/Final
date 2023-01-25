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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onlinepizza.controller.CouponController;
import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.entity.Coupon;
import com.onlinepizza.exception.CouponIdNotFoundException;
import com.onlinepizza.exception.InvalidCouponOperationException;
import com.onlinepizza.service.CouponService;



@ExtendWith(MockitoExtension.class)
public class CouponControllerTest {

	@Mock
	private CouponService couponService;
	
	@Mock
	private ModelMapper mapper;
	
	@InjectMocks
	private CouponController couponController;
	
	private Coupon coupon;
	private CouponDTO couponDto;
	
	private List<CouponDTO> coupons;
	
	@BeforeEach
	void setUp() throws Exception{
		
		couponDto = new CouponDTO();
		couponDto.setCouponId(1);
		couponDto.setCouponName("golden");
		couponDto.setCouponType("golden");
		couponDto.setCouponDesciption("20% off");
		
		coupon =mapper.map(couponDto,Coupon.class);
		
		coupons = new ArrayList<>();
		coupons.add(couponDto);
	}
	
	@Test
	void testAddCoupon() {
		when(couponService.addCoupans(couponDto)).thenReturn(couponDto);
		ResponseEntity<CouponDTO> response=couponController.addCoupons(couponDto);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}
	
	@Test
	void testDeleteCoupon() throws CouponIdNotFoundException {
		when(couponService.deleteCoupans(1)).thenReturn(true);
		ResponseEntity<CouponDTO> response=couponController.deleteCoupon(1);
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testViewCoupon() {
		when(couponService.viewCoupons()).thenReturn(coupons);
		ResponseEntity<List<CouponDTO>> response=couponController.viewCoupons();
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	
	@Test
	void testPutCoupon() throws InvalidCouponOperationException {
		when(couponService.editCoupans(couponDto, 1)).thenReturn(couponDto);
		ResponseEntity<CouponDTO> response=couponController.editCoupon(1, couponDto);
		assertEquals(HttpStatus.ACCEPTED,response.getStatusCode());
	}
	
}
