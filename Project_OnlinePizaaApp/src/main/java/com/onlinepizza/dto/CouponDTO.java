package com.onlinepizza.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class CouponDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private int couponId;
	@NotEmpty(message = "Name must not be empty")
	private String couponName;
	@NotNull
	private String couponType;
	private String couponDesciption;

	

	public CouponDTO() {}
	public CouponDTO( int couponId,  String couponName, String couponType, String couponDesciption) {
		super();
		this.couponId = couponId;
		this.couponName = couponName;
		this.couponType = couponType;
		this.couponDesciption = couponDesciption;
		
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponDesciption() {
		return couponDesciption;
	}

	public void setCouponDesciption(String couponDesciption) {
		this.couponDesciption = couponDesciption;
	}



}
