package com.example.CouponsExample.Entities.Repo;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.CouponsExample.Entities.beans.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

    @Query("SELECT с FROM Company с")
    Optional<Collection<Company>> findAllCompanies();

    @Query("SELECT DISTINCT c FROM Company c WHERE UPPER(c.name) LIKE UPPER(:name)")
    Optional<Company> findByName(String name);
    
    @Query("SELECT DISTINCT c FROM Company c WHERE UPPER(c.email) LIKE UPPER(:email)")
    Optional<Company> findByEmail(String email);
    
    @Query("SELECT DISTINCT c FROM Company c WHERE UPPER(c.email) LIKE UPPER(:email) AND c.password = :password")
    Optional<Company> findCompanyByEmailAndPassword(String email, String password);

	Optional<Company> findCompanyByCompanyId(int id);
	
 
}