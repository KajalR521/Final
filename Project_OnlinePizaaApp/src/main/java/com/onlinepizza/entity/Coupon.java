package com.onlinepizza.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "coupon_table")
@Scope("Prototype")
public class Coupon {

	@Id
	@SequenceGenerator(name="couponSeq",initialValue = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "couponSeq")
	private int couponId;
	private String couponName;
	private String couponType;
	private String couponDesciption;

	public Coupon() {
	}

	public Coupon(int couponId, String couponName, String couponType, String couponDesciption) {
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
		if(couponType.equals("Silver")) {
			System.out.println("10% off");
		}
		else if(couponType.equals("Golden")) {
			System.out.println("20% off");
		}
		this.couponType = couponType;
	}

	public String getCouponDesciption() {
		return couponDesciption;
	}

	public void setCouponDesciption(String couponDesciption) {
		this.couponDesciption = couponDesciption;
	}
	

	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", couponName=" + couponName + ", couponType=" + couponType
				+ ", couponDesciption=" + couponDesciption +  "]";
	}

}
