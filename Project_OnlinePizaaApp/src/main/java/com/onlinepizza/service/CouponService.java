package com.onlinepizza.service;

import java.util.List;

import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.entity.Coupon;
import com.onlinepizza.exception.CouponIdNotFoundException;
import com.onlinepizza.exception.InvalidCouponOperationException;

public interface CouponService {
	
	CouponDTO addCoupans(CouponDTO coupondto);

	CouponDTO editCoupans(CouponDTO couponDto,int couponId) throws InvalidCouponOperationException;

	boolean deleteCoupans(int couponId) throws CouponIdNotFoundException;

	List<CouponDTO> viewCoupons();
}
