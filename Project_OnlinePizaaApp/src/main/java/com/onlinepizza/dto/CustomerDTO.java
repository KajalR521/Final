package com.onlinepizza.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.onlinepizza.entity.PizzaOrder;

public class CustomerDTO {

	@JsonProperty(access = Access.READ_ONLY)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;

	@NotEmpty(message = "Name may not be null")
	private String cName;

	@NotEmpty
	@Email(message = "Wrong email")
	private String cEmail;

	@NotEmpty
	private String cAddress;

	@NotEmpty
	//@Size(min = 10, message = "mobile number is required")
	@Length(min = 10)
	private long cMobileNo;
	
	//private List<PizzaOrder> pizzaOrder = new ArrayList<>();
	private List<Integer> pizzaOrder = new ArrayList<>();

	public CustomerDTO() {
	}

//	public CustomerDTO(List<PizzaOrder> pizzaOrder) {
//		super();
//		this.pizzaOrder = pizzaOrder;
//	}
//
//	public List<PizzaOrder> getPizzaOrder() {
//		return pizzaOrder;
//	}
//
//	public void setPizzaOrder(List<PizzaOrder> pizzaOrder) {
//		this.pizzaOrder = pizzaOrder;
//	}

	public CustomerDTO(int cId, String cName, String cEmail, @NotEmpty String cAddress, long cMobileNo) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cEmail = cEmail;
		this.cAddress = cAddress;
		this.cMobileNo = cMobileNo;
	}

	public CustomerDTO(List<Integer> pizzaOrder) {
	super();
	this.pizzaOrder = pizzaOrder;
}

	public List<Integer> getPizzaOrder() {
		return pizzaOrder;
	}

	public void setPizzaOrder(List<Integer> pizzaOrder) {
		this.pizzaOrder = pizzaOrder;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public long getcMobileNo() {
		return cMobileNo;
	}

	public void setcMobileNo(long cMobileNo) {
		this.cMobileNo = cMobileNo;
	}

}
