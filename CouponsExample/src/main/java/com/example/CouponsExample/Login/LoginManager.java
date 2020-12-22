package com.example.CouponsExample.Login;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.example.CouponsExample.Entities.Service.AdminFacade;
import com.example.CouponsExample.Entities.Service.ClientFacade;
import com.example.CouponsExample.Entities.Service.CompanyFacade;
import com.example.CouponsExample.Entities.Service.CustomerFacade;
import com.example.CouponsExample.Exceptions.Login.LoginFailedException;

@Service
public class LoginManager {

	private AdminFacade admin;
	private CompanyFacade company;
	private CustomerFacade customer;

	public LoginManager(AdminFacade admin, CompanyFacade company, CustomerFacade customer) {
		this.admin = admin;
		this.company = company;
		this.customer = customer;
	}

	// build all the methods for the client type login into the system
	public ClientFacade login(String email, String password, ClientType clientType)
			throws SQLException, LoginFailedException {

		switch (clientType) {

		case Administrator:
			if (admin.login(email, password))
				return admin;

		case Company:
			if (company.login(email, password))
				return company;

		case Customer:
			if (customer.login(email, password))
				return customer;
		}
		throw new LoginFailedException();

	}

}
