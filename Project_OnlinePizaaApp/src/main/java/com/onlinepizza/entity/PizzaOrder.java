package com.onlinepizza.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

import net.bytebuddy.utility.nullability.MaybeNull;

@Entity
@Table(name = "pizzaordertable")
@Scope("Prototype")
public class PizzaOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingOrderId;
	
	private LocalDate dateOfOrder;
	private String transactionMode;
	private String size;
	private double totalCost;
	private List<Integer> quantity;
	@OneToMany
	private Pizza pizza;

//	@OneToMany
//	private List<OrderQuantity> quantity;
	
	
	@OneToOne
	private Coupon coupon;

	public PizzaOrder() {}
	public PizzaOrder(int bookingOrderId, LocalDate dateOfOrder, String transactionMode, String size, double totalCost,
			List<Integer> quantity, Coupon coupon) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.dateOfOrder = dateOfOrder;
		this.transactionMode = transactionMode;
		this.size = size;
		this.totalCost = totalCost;
		this.quantity = quantity;
		this.coupon = coupon;
	}

	public PizzaOrder(List<Integer> quantity, Pizza pizza) {
		super();
		this.quantity = quantity;
		this.pizza = pizza;
	}
	public List<Integer> getQuantity() {
		return quantity;
	}
	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
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

//	public List<OrderQuantity> getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(List<OrderQuantity> quantity) {
//		this.quantity = quantity;
//	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	@Override
	public String toString() {
		return "PizzaOrder [bookingOrderId=" + bookingOrderId + ", dateOfOrder=" + dateOfOrder + ", transactionMode="
				+ transactionMode + ", size=" + size + ", totalCost=" + totalCost + ", quantity=" + quantity
				+ ", pizza=" + pizza + ", coupon=" + coupon + "]";
	}

	

}
