package com.example.CouponsExample;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.CouponsExample.Entities.beans.Category;
import com.example.CouponsExample.Entities.beans.Company;
import com.example.CouponsExample.Entities.beans.Coupon;
import com.example.CouponsExample.Entities.beans.Customer;
import com.example.CouponsExample.Login.ClientType;
import com.example.CouponsExample.Login.LoginManager;
import com.example.CouponsExample.Entities.Service.AdminFacade;
import com.example.CouponsExample.Entities.Service.CompanyFacade;
import com.example.CouponsExample.Entities.Service.CustomerFacade;

@Configurable

public class Test {

	public static void main(String[] args) {

		// The department that examines all the methods and options we have
		// built

		try {
			ConfigurableApplicationContext ctx = SpringApplication.run(CouponsExampleApplication.class, args);

			LoginManager login = ctx.getBean(LoginManager.class);

			Company comp1 = new Company(1, "first company", "email first", "password first");
			Company comp2 = new Company(2, "second company", "email second", "password second");
			Company comp3 = new Company(3, "third company", "email third", "password third");
			Company comp4 = new Company(4, "fourth company", "email fourth", "password fourth");

			Customer cust1 = new Customer(1, "itay", "harari", "itay@itay", "1234");
			Customer cust2 = new Customer(2, "yaniv", "mittelman", "Yaniv@yaniv", "4321");
			Customer cust3 = new Customer(3, "Margarita", "Rifkin", "Margarita@rifkin", "1111");
			Customer cust4 = new Customer(4, "yos", "yossi", "Yossi@yossi", "2222");

			Coupon coup1 = new Coupon(1, Category.Food, "food coupon", "still food coupon", Date.valueOf("2020-01-01"),
					Date.valueOf("2020-01-31"), 1, 1, "image");
			Coupon coup2 = new Coupon(2, Category.Electricity, "Electricity coupon", "one more coupon",
					Date.valueOf("2020-03-01"), Date.valueOf("2020-12-01"), 2, 2, "moreImage");
			Coupon coup3 = new Coupon(3, Category.Furniture, "Furniture coupon", "still food coupon",
					Date.valueOf("2020-01-01"), Date.valueOf("2020-01-31"), 3, 3, "image");
			Coupon coup4 = new Coupon(4, Category.Garden, "Garden coupon", "one more coupon",
					Date.valueOf("2020-03-01"), Date.valueOf("2020-05-31"), 4, 4, "moreImage");
		

			AdminFacade admin = (AdminFacade) login.login("admin", "1234", ClientType.Administrator);
			if (admin != null) {

				admin.addCompany(comp1);
				admin.addCompany(comp2);
				admin.addCompany(comp3);
				admin.addCompany(comp4);
				comp3 = new Company(3, "changed company", "email changed", "password changed");
				admin.updateCompany(comp3);
				admin.deleteCompany(comp2.getCompanyId());

				System.out.println(admin.getAllCompanies());

				System.out.println(admin.getOneCompany(1));

				System.out.println("***************    so far for companies under admin    *********************");

				admin.addCustomer(cust1);
				admin.addCustomer(cust2);
				admin.addCustomer(cust3);
				admin.addCustomer(cust4);

				cust3 = new Customer(3, "Nir", "Gal", "Nir@Gal", "2222");
				admin.updateCustomer(cust3);

				admin.deleteCustomer(2);

				System.out.println(admin.getAllCustomers());

				System.out.println(admin.getCustomerById(3));

				System.out.println("***************    so far for customers under admin    *********************");

			}

			CompanyFacade company = (CompanyFacade) login.login("email first", "password first", ClientType.Company);
			if (company != null) {

				company.addCoupon(coup1);
				company.addCoupon(coup2);
				company.addCoupon(coup3);
				company.addCoupon(coup4);

				
				System.out.println(company.getAllCompanyCoupons());
			    
				coup2 = new Coupon(2, Category.Electricity, "Electricity coupon UPDATED", "one more coupon",
						Date.valueOf("2020-03-01"), Date.valueOf("2020-12-31"), 4, 4, "moreImage");
				company.updateCoupon(coup2);
				company.deleteCoupon(3);

				System.out.println(company.getAllCompanyCoupons());
				System.out.println(company.getAllCompanyCouponsByCategory(Category.Food));
				System.out.println(company.getAllCompanyCouponsByMaxPrice(2.0));

				System.out.println("***************    so far for companies    *********************");

			}

			CustomerFacade customer = (CustomerFacade) login.login("itay@itay", "1234", ClientType.Customer);
			if (customer != null) {
				cust1 = new Customer(1, "itay", "harari", "itay@itay", "1234");
				coup4 = new Coupon(4, Category.Garden, "Garden coupon", "one more coupon",
						Date.valueOf("2020-03-01"), Date.valueOf("2020-05-31"), 4, 4, "moreImage");
				System.out.println(cust1);
				System.out.println(coup4);
				cust1.purchaseCoupon(coup4);
				System.out.println(customer.getAllAvailableCoupons());
				System.out.println(customer.getPurchasedCouponsByType(Category.Garden));
				System.out.println(customer.getPurchasedCouponsByPrice(4));
				System.out.println("***************    so far for customers    *********************");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
