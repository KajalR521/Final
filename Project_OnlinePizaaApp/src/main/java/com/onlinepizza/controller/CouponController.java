package com.onlinepizza.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinepizza.dto.CouponDTO;
import com.onlinepizza.exception.CouponIdNotFoundException;
import com.onlinepizza.exception.InvalidCouponOperationException;
import com.onlinepizza.service.CouponService;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	private CouponService cService;

	@PostMapping
	public ResponseEntity<CouponDTO> addCoupons(@Valid @RequestBody CouponDTO cDto) {
		return new ResponseEntity(cService.addCoupans(cDto), HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<CouponDTO> editCoupon(@PathVariable("id") int id, @RequestBody CouponDTO couponDto)
			throws InvalidCouponOperationException {
		return new ResponseEntity(cService.editCoupans(couponDto, id), HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CouponDTO> deleteCoupon(@PathVariable("id") int id) throws CouponIdNotFoundException {
		return new ResponseEntity(cService.deleteCoupans(id), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<CouponDTO>> viewCoupons() {
		return new ResponseEntity(cService.viewCoupons(), HttpStatus.OK);

	}
}
