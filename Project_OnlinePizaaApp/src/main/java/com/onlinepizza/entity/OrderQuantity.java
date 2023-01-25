package com.onlinepizza.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;

@Entity
@Scope("Prototype")
public class OrderQuantity {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	
	@OneToOne
	private Pizza pizza;
	private int quantity;

	public OrderQuantity() {
	}

	public OrderQuantity(int orderId, Pizza pizza, int quantity) {
		super();
		this.orderId = orderId;
		this.pizza = pizza;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderQuantity [orderId=" + orderId + ", pizza=" + pizza + ", quantity=" + quantity + "]";
	}



}
