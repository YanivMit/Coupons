package com.example.CouponsExample.Web.RestControllers;

import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.CouponsExample.Entities.Repo.TokenRepo;
import com.example.CouponsExample.Entities.Service.AdminFacade;

import com.example.CouponsExample.Entities.beans.Company;
import com.example.CouponsExample.Entities.beans.Coupon;
import com.example.CouponsExample.Entities.beans.Customer;
import com.example.CouponsExample.Exceptions.Company.CompanyEmailDuplicate;
import com.example.CouponsExample.Exceptions.Company.CompanyException;
import com.example.CouponsExample.Exceptions.Coupon.CouponSystemException;
import com.example.CouponsExample.Exceptions.Customer.CustomerDoesNotExist;
import com.example.CouponsExample.Exceptions.Customer.CustomerEmailDuplicate;
import com.example.CouponsExample.Exceptions.Login.LoginFailedException;
import com.example.CouponsExample.Login.ClientType;

@RestController
@RequestMapping(path = "admin")
@Scope("request")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AdminController {

	private final AdminFacade AdminFacade;

	private final TokenRepo tokenRepo;
	private final HttpServletRequest request;

	@Autowired
	public AdminController(AdminFacade AdminFacade, TokenRepo tokenRepo, HttpServletRequest request) {
		this.AdminFacade = AdminFacade;
		this.tokenRepo = tokenRepo;
		this.request = request;
	}

	/**
	 * COMPANY methods
	 */
	@PostMapping("companies")
	public ResponseEntity<?> addCompany(@RequestBody Company company) {
		try {
			AdminFacade.addCompany(company);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CompanyEmailDuplicate | CompanyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("companies/{companyId}")
	public ResponseEntity<?> getCompanyById(@PathVariable int companyId) {
		try {
			Company company = AdminFacade.getOneCompany(companyId);
			return new ResponseEntity<>(company, HttpStatus.OK);
		} catch (CompanyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("companies")
	public ResponseEntity<?> getAllCompanies() {
		try {
			Collection<Company> companies = AdminFacade.getAllCompanies();
			return new ResponseEntity<>(companies, HttpStatus.OK);
		} catch (CompanyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "companies", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCompany(@RequestBody Company company) {
		try {
			AdminFacade.updateCompany(company);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CompanyEmailDuplicate | CompanyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("companies/{companyId}")
	public ResponseEntity<?> deleteCompany(@PathVariable int companyId) {
		try {
			AdminFacade.deleteCompany(companyId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * COUPON methods
	 */
	@PostMapping("coupons/{companyId}")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @PathVariable int companyId) {
		try {
			AdminFacade.addCoupon(companyId, coupon);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CouponSystemException | CompanyException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("coupons/{couponId}")
	public ResponseEntity<?> getCouponById(@PathVariable int couponId) {
		try {
			Coupon coupon = AdminFacade.getCouponById(couponId);
			return new ResponseEntity<>(coupon, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("coupons")
	public ResponseEntity<?> getAllCoupons() {
		try {
			Collection<Coupon> coupons = AdminFacade.getAllCoupons();
			return new ResponseEntity<>(coupons, HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "coupons/{companyId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @PathVariable int companyId) {
		try {
			AdminFacade.updateCoupon(companyId, coupon);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CouponSystemException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("coupons/{couponId}")
	public ResponseEntity<?> deleteCoupon(@PathVariable int couponId) {
		try {
			AdminFacade.deleteCoupon(couponId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * CUSTOMER methods
	 */
	@PostMapping("customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {
			AdminFacade.addCustomer(customer);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (CustomerEmailDuplicate e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("customers/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable int customerId) {
		try {
			Customer customer = AdminFacade.getCustomerById(customerId);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} catch (CustomerDoesNotExist e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("customers")
	public ResponseEntity<?> getAllCustomers() {
		try {
			Collection<Customer> customers = AdminFacade.getAllCustomers();
			return new ResponseEntity<>(customers, HttpStatus.OK);
		} catch (CustomerDoesNotExist e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(path = "customers", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		try {
			AdminFacade.updateCustomer(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (CustomerDoesNotExist | CustomerEmailDuplicate e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("customers/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) {
		try {
			AdminFacade.deleteCustomer(customerId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
