package com.onlinepizza.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.entity.Coupon;

@Repository
public interface CouponRepository  extends JpaRepository<Coupon, Integer>{
}
