package com.example.CouponsExample.Entities.Repo;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.CouponsExample.Entities.beans.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Query("SELECT с FROM Customer с")
	Optional<Collection<Customer>> findAllCustomers();

	Customer findCustomerByEmailAndPassword(String email, String password);
	
	Customer findCustomerByCustomerID(int Customerid);

	boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	@Query("SELECT DISTINCT c FROM Customer c WHERE UPPER(c.email) LIKE UPPER(:email)")
	Optional<Customer> findByEmail(String email);
}
