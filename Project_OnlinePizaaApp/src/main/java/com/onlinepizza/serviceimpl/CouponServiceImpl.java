package com.onlinepizza.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinepizza.dao.CouponRepository;
import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.dto.PizzaDTO;
import com.onlinepizza.entity.Coupon;
import com.onlinepizza.entity.Pizza;
import com.onlinepizza.entity.PizzaOrder;
import com.onlinepizza.exception.CouponIdNotFoundException;
import com.onlinepizza.exception.InvalidCouponOperationException;
import com.onlinepizza.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private CouponRepository cRepo;

	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	@Transactional
	public CouponDTO addCoupans(CouponDTO couponDto) {
		Coupon cobj = mapper.map(couponDto, Coupon.class);
		Coupon co=cRepo.save(cobj);
		CouponDTO cobj1 = mapper.map(co, CouponDTO.class);
		return cobj1;
	}

	@Override
	@Transactional
	public CouponDTO editCoupans(CouponDTO couponDto,int couponId) throws InvalidCouponOperationException {
		Optional<Coupon> c=cRepo.findById(couponId);
		if (c.isPresent()) {
			Coupon cobj = mapper.map(couponDto, Coupon.class);
			cRepo.save(cobj);
		CouponDTO editCoupon=mapper.map(c,CouponDTO.class);
		return editCoupon;
		} else {
			throw new InvalidCouponOperationException("Invalid Coupon");
		}
		
	}
	
	@Override
	@Transactional
	public boolean deleteCoupans(int couponId) throws CouponIdNotFoundException {
		
		Optional<Coupon> cFind = cRepo.findById(couponId);
		
		if (cFind.isPresent()) {
			cRepo.deleteById(couponId);
			return true;
		}
		return false;
	}

	@Override
	public List<CouponDTO> viewCoupons() {
		List<Coupon> cobj1 =cRepo.findAll();
		
		List<CouponDTO> cList=new ArrayList<>();
		//cList.forEach(cobj->cList.add(mapper.map(cobj1, CouponDTO.class)));
		
//		cobj1=mapper.map(cList, Coupon.class);
//		cList.add(cobj1);
		cList = mapper.map(cobj1, new TypeToken<List<CouponDTO>>() {}.getType());
		System.out.println(cList);
		//List<CouponDTO>cobj3=cobj2;
		return cList;
	}
	


}
