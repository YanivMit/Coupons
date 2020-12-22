package com.example.CouponsExample.Web.RestControllers;

import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CouponsExample.Entities.Service.CustomerFacade;
import com.example.CouponsExample.Entities.beans.Category;
import com.example.CouponsExample.Entities.beans.Coupon;
import com.example.CouponsExample.Entities.beans.Customer;
import com.example.CouponsExample.Entities.beans.Token;
import com.example.CouponsExample.Exceptions.Login.LoginFailedException;
import com.example.CouponsExample.Exceptions.Coupon.CouponSystemException;
import com.example.CouponsExample.Exceptions.Customer.CustomerException;
import com.example.CouponsExample.Login.ClientType;
import com.example.CouponsExample.Entities.Repo.CustomerRepo;
import com.example.CouponsExample.Entities.Repo.TokenRepo;

@RestController
@RequestMapping(path = "customer")
@Scope("request")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CustomerController {

	private final CustomerFacade CustomerFacade;
	private final CustomerRepo CustomerRepo;
	private final TokenRepo TokenRepo;
	private final HttpServletRequest request;

	@Autowired
	public CustomerController(CustomerFacade CustomerFacade, CustomerRepo CustomerRepo, TokenRepo TokenRepo,
			HttpServletRequest request) {
		this.CustomerFacade = CustomerFacade;
		this.CustomerRepo = CustomerRepo;
		this.TokenRepo = TokenRepo;
		this.request = request;
	}

	@GetMapping("coupons/{couponId}")
	public ResponseEntity<?> addCoupon(@PathVariable int couponId) {
		try {
			CustomerFacade.addCoupon( couponId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch ( CouponSystemException | CustomerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("coupons")
	public ResponseEntity<?> getAllCustomerCoupons() {
		try {
			Collection<Coupon> coupons = CustomerFacade.getPurchasedCoupons(getCustomer());
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (LoginFailedException | CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("coupons-by-type/{couponType}")
	public ResponseEntity<?> getPurchasedCouponsByType(@PathVariable Category category) throws CustomerException {
		try {
			Collection<Coupon> coupons = CustomerFacade.getPurchasedCouponsByType(category);
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("coupons-by-price/{price}")
	public ResponseEntity<?> getPurchasedCouponsByPrice(@PathVariable double price) throws CustomerException {
		try {
			Collection<Coupon> coupons = CustomerFacade.getPurchasedCouponsByPrice(price);
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("coupons-available")
	public ResponseEntity<?> getAllAvailableCoupons() {
		try {
			Collection<Coupon> coupons = CustomerFacade.getAllAvailableCoupons();
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	private Customer getCustomer() throws LoginFailedException {
		Token token = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("auth")) {
				token = TokenRepo.findByClientTypeAndToken(ClientType.Customer, c.getValue())
						.orElseThrow(() -> new LoginFailedException("Authorization is failed, please try again."));
			}
		}
		return CustomerRepo.findById(token.getUserId())
				.orElseThrow(() -> new LoginFailedException("Authorization is failed, please try again."));
	}
}