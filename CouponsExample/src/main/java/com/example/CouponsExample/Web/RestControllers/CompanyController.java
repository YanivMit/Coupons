package com.example.CouponsExample.Web.RestControllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CouponsExample.Entities.Service.CompanyFacade;
import com.example.CouponsExample.Entities.beans.Category;
import com.example.CouponsExample.Entities.beans.Coupon;
import com.example.CouponsExample.Exceptions.Company.CompanyException;
import com.example.CouponsExample.Exceptions.Coupon.CouponSystemException;

@RestController
@RequestMapping(path = "company")
@Scope("request")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CompanyController {

	private final CompanyFacade companyFacade;

	@Autowired
	public CompanyController(CompanyFacade companyFacade, HttpServletRequest request) {
		this.companyFacade = companyFacade;
	}

	@PostMapping("coupons")
	public ResponseEntity<?> addCoupon(@RequestBody  Coupon coupon) throws CompanyException {
		try {
			companyFacade.addCoupon(coupon);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CompanyException | CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("coupons-by-type/{couponType}")
	public ResponseEntity<?> getAllCompanyCouponsByCategory(@PathVariable Category category) {
		try {
			Collection<Coupon> coupons = companyFacade.getAllCompanyCouponsByCategory(category);
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CompanyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("coupons-by-price/{price}")
	public ResponseEntity<?> getAllCompanyCouponsByMaxPrice(@PathVariable double price) {
		try {
			Collection<Coupon> coupons = companyFacade.getAllCompanyCouponsByMaxPrice(price);
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CompanyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
