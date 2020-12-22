package com.example.CouponsExample.Entities.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CouponsExample.Entities.beans.Category;
import com.example.CouponsExample.Entities.beans.Coupon;
import com.example.CouponsExample.Entities.beans.Customer;
import com.example.CouponsExample.Exceptions.Coupon.CouponSystemException;
import com.example.CouponsExample.Exceptions.Coupon.CouponTitleException;
import com.example.CouponsExample.Exceptions.Customer.CustomerException;
import com.example.CouponsExample.Entities.Repo.CustomerRepo;
import com.example.CouponsExample.Entities.Repo.CouponRepo;

@Service
public class CustomerFacade extends ClientFacade {

	private final CustomerRepo CustomerRepo;
	private final CouponRepo CouponRepo;
	private int customerid;

	@Autowired
	public CustomerFacade(CustomerRepo CustomerRepo, CouponRepo CouponRepo) {
		this.CustomerRepo = CustomerRepo;
		this.CouponRepo = CouponRepo;
	}

	@Override
	public boolean login(String email, String password) {

		if (CustomerRepo.findCustomerByEmailAndPassword(email, password) != null) {
			customerid = CustomerRepo.findCustomerByEmailAndPassword(email, password).getId();
			System.out.println("You are now logged in as customer :)");
			return true;
		}
		return false;
	}
	// Build all the methods for the customer user

	public void addCoupon(int couponId)
			throws CouponTitleException, CouponSystemException, CustomerException {
		
		// checking if the customer have existing coupon
		this.isCustomerHasCoupon(couponId);

		Coupon coupon = CouponRepo.findById(couponId)
				.orElseThrow(() -> new CouponSystemException("This coupon doesn't exist."));

		// checking if the date is valid coupon
		if (coupon.getEndDate().after(new Date(System.currentTimeMillis()))) {

			// Checking amount
			if (coupon.getAmount() > 0) {

				coupon.setAmount(coupon.getAmount() - 1);
				CouponRepo.save(coupon);

				getCustomerDetails().purchaseCoupon(coupon);
				CustomerRepo.save(getCustomerDetails());

			} else {
				throw new CouponSystemException("This coupon is not available");
			}
		} else {
			throw new CouponSystemException("This coupon is expired.");
		}
	}

	public Collection<Coupon> getPurchasedCoupons(Customer customer) throws CouponSystemException {
		return CouponRepo.findAllCustomerCoupons(customer.getId())
				.orElseThrow(() -> new CouponSystemException("You have no coupons."));
	}

	public Collection<Coupon> getPurchasedCouponsByType(Category category)
			throws CouponSystemException, CustomerException {
		return CouponRepo.findAllCustomerCouponsByType(getCustomerDetails().getId(), category)
				.orElseThrow(() -> new CouponSystemException("You have no coupons by type: " + category + "."));
	}

	public Collection<Coupon> getPurchasedCouponsByPrice(double price) throws CouponSystemException, CustomerException {
		return CouponRepo.findAllCustomerCouponsByPrice(getCustomerDetails().getId(), price)
				.orElseThrow(() -> new CouponSystemException("You have no coupons by type: " + price + "."));
	}

	public Collection<Coupon> getAllAvailableCoupons() throws CouponSystemException {
		return CouponRepo.findAllAvailableCoupons()
				.orElseThrow(() -> new CouponSystemException("There are no available coupons."));
	}

	private void isCustomerHasCoupon(int couponId)
			throws CustomerException {
		Optional<Coupon> isCustomerHasCoupon = CouponRepo.findCustomerCoupon(this.customerid, couponId);
		if (isCustomerHasCoupon.isPresent()) {
			throw new CustomerException("You already have this coupon.");
		}

	}
	
	public Customer getCustomerDetails() throws CustomerException {
		if(CustomerRepo.findCustomerByCustomerID(customerid)!=null) {
			return CustomerRepo.findCustomerByCustomerID(customerid);			
		} else {
			throw new CustomerException();
		}
	}
}
