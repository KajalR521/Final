package com.onlinepizza.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { CouponIdNotFoundException.class })
	public ErrorInformation handleConflict(CouponIdNotFoundException e, HttpServletRequest req) {
		String msg = e.getMessage();
		String uri = req.getRequestURI();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(uri, msg, dt);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { CustomerIdNotFoundException.class })
	public ErrorInformation handleConflict(CustomerIdNotFoundException e, HttpServletRequest req) {
		String msg = e.getMessage();
		String uri = req.getRequestURI();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(uri, msg, dt);

	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { InvalidCouponOperationException.class })
	public ErrorInformation handleConflict(InvalidCouponOperationException e, HttpServletRequest req) {
		String msg = e.getMessage();
		String uri = req.getRequestURI();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(uri, msg, dt);
		
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { InvalidCustomerException.class })
	public ErrorInformation handleConflict(InvalidCustomerException e, HttpServletRequest req) {
		String msg = e.getMessage();
		String uri = req.getRequestURI();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(uri, msg, dt);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { InvalidMinCostException.class })
	public ErrorInformation handleConflict(InvalidMinCostException e, HttpServletRequest req) {
		String msg = e.getMessage();
		String uri = req.getRequestURI();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(uri, msg, dt);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { InvalidSizeException.class })
	public ErrorInformation handleConflict(InvalidSizeException e, HttpServletRequest req) {
		String msg = e.getMessage();
		String uri = req.getRequestURI();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(uri, msg, dt);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { OrderIdNotFoundException.class })
	public ErrorInformation handleConflict(OrderIdNotFoundException e, HttpServletRequest req) {
		String msg = e.getMessage();
		String uri = req.getRequestURI();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(uri, msg, dt);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { PizzaIdNotFoundException.class })
	public ErrorInformation handleConflict(PizzaIdNotFoundException e, HttpServletRequest req) {
		String msg = e.getMessage();
		String uri = req.getRequestURI();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(uri, msg, dt);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ErrorInformation handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest req) {
		String msg = "validation faild";
		FieldError error = ex.getFieldError();
		if (error != null)
			msg = error.getDefaultMessage();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(req.getRequestURI(), msg, dt);

	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { Exception.class })
	public ErrorInformation handleConflict(Exception e, HttpServletRequest req) {
		String msg = e.getMessage();
		e.printStackTrace();
		String uri = req.getRequestURI();
		LocalDateTime dt = LocalDateTime.now();
		return new ErrorInformation(uri, msg, dt);
	}

}
