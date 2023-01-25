package com.onlinepizza.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
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
import org.modelmapper.ModelMapper;

import com.onlinepizza.dao.CouponRepository;
import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.entity.Coupon;
import com.onlinepizza.exception.CouponIdNotFoundException;
import com.onlinepizza.exception.InvalidCouponOperationException;
import com.onlinepizza.serviceimpl.CouponServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CouponServiceImplTest {

	@Mock
	private CouponRepository repo;

	@InjectMocks
	private CouponServiceImpl service;
	@Mock
	private ModelMapper mapper;

	private Coupon coupon;
	private CouponDTO cp;
	private List<CouponDTO> coupons;
	
	public CouponServiceImplTest() {
		//service = new CouponServiceImpl();
		coupons = new ArrayList<>();
	}
	@BeforeEach
	private void initEach() {
		coupon=new Coupon();
		coupon.setCouponId(21);
		coupon.setCouponName("golden");
		coupon.setCouponType("golden");
		coupon.setCouponDesciption("50% off");
		//-cp.setCouponPizzaId(500);
		coupon =mapper.map(cp,Coupon.class);
		coupons.add(cp);
	}

	@Test
	void viewCouponsTest() {
		when(service.viewCoupons()).thenReturn(coupons);
		assertEquals(service.viewCoupons(), coupons);
	}


	
//	@Test
//	void deleteCouponTest() {
//		boolean isDelete;
//		try {
//			isDelete =service.deleteCoupans(21);
//			assertFalse(isDelete);
//		} catch (CouponIdNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("Can't able to delete Coupon");
//		}
//
//	}
}
