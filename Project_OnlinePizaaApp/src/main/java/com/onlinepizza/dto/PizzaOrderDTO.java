package com.onlinepizza.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.onlinepizza.entity.Coupon;
import com.onlinepizza.entity.Pizza;

public class PizzaOrderDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private int bookingOrderId;

	private LocalDate dateOfOrder;

	@NotEmpty
	private String transactionMode;


	private List<Integer> quantity;

	@NotEmpty(message = "Size is must")
	private String size;

	@JsonProperty(access = Access.READ_ONLY)
	private double totalCost;

	
	private List<Integer> pizza = new ArrayList<>();
	private Integer coupon;
	
	public PizzaOrderDTO() {}
	public PizzaOrderDTO(int bookingOrderId,  LocalDate dateOfOrder, @NotEmpty String transactionMode,
			List<Integer> quantity, @NotEmpty(message = "Size is must") String size, double totalCost,
			List<Integer> pizza, Integer coupon) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.dateOfOrder = dateOfOrder;
		this.transactionMode = transactionMode;
		this.quantity = quantity;
		this.size = size;
		this.totalCost = totalCost;
		this.pizza = pizza;
		this.coupon = coupon;
	}
	public int getBookingOrderId() {
		return bookingOrderId;
	}
	public void setBookingOrderId(int bookingOrderId) {
		this.bookingOrderId = bookingOrderId;
	}
	public LocalDate getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public List<Integer> getQuantity() {
		return quantity;
	}
	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public List<Integer> getPizza() {
		return pizza;
	}
	public void setPizza(List<Integer> pizza) {
		this.pizza = pizza;
	}
	public Integer getCoupon() {
		return coupon;
	}
	public void setCoupon(Integer coupon) {
		this.coupon = coupon;
	}
	
	@Override
	public String toString() {
		return "PizzaOrderDTO [bookingOrderId=" + bookingOrderId + ", dateOfOrder=" + dateOfOrder + ", transactionMode="
				+ transactionMode + ", quantity=" + quantity + ", size=" + size + ", totalCost=" + totalCost
				+ ", pizza=" + pizza + ", coupon=" + coupon + "]";
	}

	

}
