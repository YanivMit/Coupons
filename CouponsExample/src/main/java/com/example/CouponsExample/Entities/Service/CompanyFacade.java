package com.example.CouponsExample.Entities.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.CouponsExample.Entities.Repo.CompanyRepo;
import com.example.CouponsExample.Entities.Repo.CouponRepo;
import com.example.CouponsExample.Entities.beans.Category;
import com.example.CouponsExample.Entities.beans.Company;
import com.example.CouponsExample.Entities.beans.Coupon;
import com.example.CouponsExample.Exceptions.Company.CompanyException;
import com.example.CouponsExample.Exceptions.Coupon.CouponExpiredException;
import com.example.CouponsExample.Exceptions.Coupon.CouponSystemException;
import com.example.CouponsExample.Exceptions.Coupon.CouponTitleException;
import com.example.CouponsExample.Exceptions.Coupon.CouponUnavaliableException;

@Service
@Scope("prototype")
public class CompanyFacade extends ClientFacade {

	private final CouponRepo couponRepo;
	private final CompanyRepo compRepo;
	private int companyID;

	@Autowired
	public CompanyFacade(CouponRepo couponRepo, CompanyRepo compRepo) {
		this.couponRepo = couponRepo;
		this.compRepo = compRepo;
	}
	// Build all the methods for the company user

	@Override
	public boolean login(String email, String password) {

		if (compRep.findCompanyByEmailAndPassword(email, password) != null) {
			companyID = compRep.findCompanyByEmailAndPassword(email, password).get().getCompanyId();
			System.out.println("You are now logged in as company :)");
			return true;
		}
		return false;
	}

	public void addCoupon(Coupon coupon) throws CouponSystemException, CompanyException {
		this.isCouponTitleDuplicate(coupon.getTitle());
		coupon.setCompany(getCompanyDetails());
		couponRepo.save(coupon);

	}

	public void updateCoupon(Coupon coupon) throws CouponUnavaliableException, CompanyException, CouponExpiredException,
			CouponTitleException, CouponSystemException {

		this.isCompanyOwnsCoupon(coupon.getId());
		this.isCouponTitleDuplicate(coupon.getTitle());

		// checking that the coupon date is still valid and not expired
		if (coupon.getEndDate().after(new Date(System.currentTimeMillis()))) {

			if (coupon.getAmount() > 0) {

				if (coupon.getPrice() > 0) {

					Coupon updCoupon = couponRepo.findById(coupon.getId()).get();
					updCoupon.setEndDate(coupon.getEndDate());
					updCoupon.setPrice(coupon.getPrice());
					updCoupon.setAmount(coupon.getAmount());

					couponRepo.save(updCoupon);

				} else {
					throw new CouponUnavaliableException("Not allowed to update price to less than 1.");
				}
			} else {
				throw new CouponUnavaliableException("Not allowed to update amount to less than 1.");
			}
		} else {
			throw new CouponExpiredException("Not allowed to update the coupon to expired date.");
		}
	}

	public void deleteCoupon(int Id) throws CompanyException {
		if (this.isCompanyOwnsCoupon(Id))
			couponRepo.deleteById(Id);

	}

	public Collection<Coupon> getAllCompanyCoupons() throws CompanyException {
		return couponRepo.findAllCompanyCoupons(getCompanyDetails().getCompanyId())
				.orElseThrow(() -> new CompanyException("You have no coupons."));
	}

	public Collection<Coupon> getAllCompanyCouponsByCategory(Category Category) throws CompanyException {
		return couponRepo.findAllCompanyCouponsByCategory(getCompanyDetails().getCompanyId(), Category)
				.orElseThrow(() -> new CompanyException("You have no coupons by type: " + Category + "."));
	}

	public Collection<Coupon> getAllCompanyCouponsByMaxPrice(double price) throws CompanyException {
		return couponRepo.findAllCompanyCouponsByMaxPrice(getCompanyDetails().getCompanyId(), price)
				.orElseThrow(() -> new CompanyException("You have no coupons by price: " + price + "."));
	}

	public Company getCompanyDetails() throws CompanyException {
		getOneCompany(companyID).setCoupons(getOneCompany(companyID).getCoupons());
		return compRepo.findCompanyByCompanyId(companyID).orElseThrow(() -> new CompanyException());
	}

	public Company getOneCompany(int id) throws CompanyException {
		return compRepo.findCompanyByCompanyId(id).get();
	}

	public void isCouponTitleDuplicate(String couponTitle) throws CouponSystemException {
		Optional<Coupon> isCouponTitleDuplicate = couponRepo.findByTitle(couponTitle);
		if (isCouponTitleDuplicate.isPresent()) {
			throw new CouponSystemException("Coupon title: " + couponTitle + " already exists.");
		}
	}

	// PROBLEM
	public boolean isCompanyOwnsCoupon(int Id) throws CompanyException {
		if (couponRepo.findCompanyCoupon(getCompanyDetails().getCompanyId(), Id) != null) {
			return true;
		} else {
			return false;
		}
		// couponRepo.findCompanyCoupon(getCompanyDetails().getCompanyId(),
		// Id).orElseThrow(() -> new CompanyException("You don't own this coupon."));
	}
}
