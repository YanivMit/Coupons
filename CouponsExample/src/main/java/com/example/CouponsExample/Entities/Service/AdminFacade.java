package com.example.CouponsExample.Entities.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CouponsExample.Entities.Repo.CompanyRepo;
import com.example.CouponsExample.Entities.Repo.CouponRepo;
import com.example.CouponsExample.Entities.Repo.CustomerRepo;
import com.example.CouponsExample.Entities.Repo.TokenRepo;
import com.example.CouponsExample.Entities.beans.Company;
import com.example.CouponsExample.Entities.beans.Coupon;
import com.example.CouponsExample.Entities.beans.Customer;
import com.example.CouponsExample.Exceptions.Company.CompanyEmailDuplicate;
import com.example.CouponsExample.Exceptions.Company.CompanyException;
import com.example.CouponsExample.Exceptions.Coupon.CouponExpiredException;
import com.example.CouponsExample.Exceptions.Coupon.CouponSystemException;
import com.example.CouponsExample.Exceptions.Coupon.CouponTitleException;
import com.example.CouponsExample.Exceptions.Customer.CustomerDoesNotExist;
import com.example.CouponsExample.Exceptions.Customer.CustomerEmailDuplicate;
import com.example.CouponsExample.Login.ClientType;


@Service
public class AdminFacade extends ClientFacade {

	private final CompanyRepo CompanyRepo;
	private final CouponRepo CouponRepo;
	private final CustomerRepo CustomerRepo;
	private final TokenRepo TokenRepo;

	@Autowired
	public AdminFacade(CompanyRepo CompanyRepo, CouponRepo CouponRepo, CustomerRepo CustomerRepo, TokenRepo TokenRepo) {
		this.CompanyRepo = CompanyRepo;
		this.CouponRepo = CouponRepo;
		this.CustomerRepo = CustomerRepo;
		this.TokenRepo = TokenRepo;
	}

	// Build all the methods for the administrator user
	public boolean login(String email, String password) {
		return (email.equals("admin") && password.equals("1234"));
	}

	/**
	 * COMPANY methods
	 */
	public void addCompany(Company company) throws CompanyException, CompanyEmailDuplicate {
		this.isCompanyNameDuplicate(company.getName(), CompanyRepo);
		this.isCompanyEmailDuplicate(company.getEmail(), CompanyRepo);

		CompanyRepo.save(company);
	}

	public void updateCompany(Company company) throws CompanyException, CompanyEmailDuplicate {
		this.isCompanyExists(company.getCompanyId(), CompanyRepo);
		this.isCompanyNameDuplicate(company.getName(), CompanyRepo);
		this.isCompanyEmailDuplicate(company.getEmail(), CompanyRepo);

		CompanyRepo.save(company);
	}

	public void deleteCompany(int companyId) {
		CompanyRepo.deleteById(companyId);
		TokenRepo.deleteAllByClientTypeAndUserId(ClientType.Company, companyId);
	}

	public Collection<Company> getAllCompanies() throws CompanyException {
		return CompanyRepo.findAllCompanies().orElseThrow(() -> new CompanyException("There are no companies."));
	}

	public Company getOneCompany(int companyId) throws CompanyException {
		return CompanyRepo.findById(companyId).orElseThrow(() -> new CompanyException("This company doesn't exist."));
	}

	/**
	 * CUSTOMER methods
	 */
	public void addCustomer(Customer customer) throws CustomerEmailDuplicate {
		this.isCustomerEmailDuplicate(customer.getEmail(), CustomerRepo);

		CustomerRepo.save(customer);
	}

	public void updateCustomer(Customer customer) throws CustomerDoesNotExist, CustomerEmailDuplicate {
		this.isCustomerExists(customer.getId(), CustomerRepo);
		this.isCustomerEmailDuplicate(customer.getEmail(), CustomerRepo);

		CustomerRepo.save(customer);
	}

	public void deleteCustomer(int customerId) {
		CustomerRepo.deleteById(customerId);
		TokenRepo.deleteAllByClientTypeAndUserId(ClientType.Customer, customerId);
	}

	public Collection<Customer> getAllCustomers() throws CustomerDoesNotExist {
		return CustomerRepo.findAllCustomers().orElseThrow(() -> new CustomerDoesNotExist("There are no customers."));
	}

	public Customer getCustomerById(int customerId) throws CustomerDoesNotExist {
		return CustomerRepo.findById(customerId)
				.orElseThrow(() -> new CustomerDoesNotExist("This customer doesn't exist."));
	}

	private void isCompanyNameDuplicate(String companyName, CompanyRepo companyRepo) throws CompanyException {
		Optional<Company> isCompanyNameDuplicate = companyRepo.findByName(companyName);
		if (isCompanyNameDuplicate.isPresent()) {
			throw new CompanyException("Company name: " + companyName + " already exists.");
		}
	}

	private void isCompanyExists(int companyId, CompanyRepo companyRepo) throws CompanyException {
		companyRepo.findCompanyByCompanyId(companyId)
				.orElseThrow(() -> new CompanyException("This company doesn't exist."));
	}

	private void isCustomerEmailDuplicate(String customerEmail, CustomerRepo customerRepo)
			throws CustomerEmailDuplicate {
		Optional<Customer> isCustomerEmailDuplicate = customerRepo.findByEmail(customerEmail);
		if (isCustomerEmailDuplicate.isPresent()) {
			throw new CustomerEmailDuplicate("Customer email: " + customerEmail + " already exists.");
		}
	}

	private void isCustomerExists(int customerId, CustomerRepo customerRepo) throws CustomerDoesNotExist {
		customerRepo.findById(customerId).orElseThrow(() -> new CustomerDoesNotExist("This customer doesn't exist."));
	}

	private void isCompanyEmailDuplicate(String companyemail, CompanyRepo companyRepo) throws CompanyEmailDuplicate {
		Optional<Company> isCompanyEmailDuplicate = companyRepo.findByEmail(companyemail);
		if (isCompanyEmailDuplicate.isPresent()) {
			throw new CompanyEmailDuplicate("Company email: " + companyemail + " already exists.");
		}
	}
 
	
	// Coupons Methods
	
	   public void addCoupon(int companyId, Coupon coupon) throws CompanyException, CouponTitleException, CouponSystemException {
	        this.isCouponTitleDuplicate(coupon.getTitle(), CouponRepo);
	        coupon.setCompany(CompanyRepo.findById(companyId)
	                .orElseThrow(() -> new CompanyException("This company doesn't exist.")));
	        CouponRepo.save(coupon);

	      
	    }

	    public Coupon getCouponById(int couponId) throws CouponSystemException {
	        return CouponRepo.findById(couponId)
	                .orElseThrow(() -> new CouponSystemException("This coupon doesn't exist."));
	    }

	    public Collection<Coupon> getAllCoupons() throws CouponSystemException {
	        return CouponRepo.findAllCoupons()
	                .orElseThrow(() -> new CouponSystemException("There are no coupons."));
	    }

	    public void updateCoupon(int companyId, Coupon coupon) throws CouponSystemException {

	        this.isCouponExists(coupon.getId(), CouponRepo);
	        this.isCouponTitleDuplicate(coupon.getTitle(), CouponRepo);
	        coupon.setCompany(CompanyRepo.findById(companyId)
	                .orElseThrow(() -> new CouponSystemException("This company doesn't exist.")));

	        if (coupon.getEndDate().after(new Date(System.currentTimeMillis()))) {

	            if (coupon.getAmount() > 0) {

	                if (coupon.getPrice() > 0) {

	                    CouponRepo.save(coupon);

	                   
	                } else {
	                    throw new CouponExpiredException("Not allowed to update price to less than 1.");
	                }
	            } else {
	                throw new CouponExpiredException("Not allowed to update amount to less than 1.");
	            }
	        } else {
	            throw new CouponExpiredException("Not allowed to update the coupon to expired date.");
	        }
	    }

	    public void deleteCoupon(int couponId) {
	        CouponRepo.deleteById(couponId);

	      
	    }
		public void isCouponTitleDuplicate(String couponTitle, CouponRepo couponRepo) throws CouponSystemException {
			Optional<Coupon> isCouponTitleDuplicate = couponRepo.findByTitle(couponTitle);
			if (isCouponTitleDuplicate.isPresent()) {
				throw new CouponSystemException("Coupon title: " + couponTitle + " already exists.");
			}
		}
	    public void isCouponExists(int couponId, CouponRepo couponRepo) throws CouponSystemException {
	        couponRepo.findById(couponId)
	                .orElseThrow(() -> new CouponSystemException("This coupon doesn't exist."));
	    }

}
