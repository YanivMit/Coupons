package com.example.CouponsExample.Entities.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.CouponsExample.Entities.Repo.CompanyRepo;
import com.example.CouponsExample.Entities.Repo.CouponRepo;
import com.example.CouponsExample.Entities.Repo.CustomerRepo;

public abstract class ClientFacade {

	@Autowired
	public CompanyRepo compRep;
	@Autowired

	public CouponRepo coupRep;
	@Autowired

	public CustomerRepo custRep;

	public abstract boolean login(String email, String password);

}
