package com.example.CouponsExample.Entities.Repo;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import com.example.CouponsExample.Entities.beans.Category;
import com.example.CouponsExample.Entities.beans.Coupon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    //   *****      for coupons
    @Query("SELECT DISTINCT c FROM Coupon c WHERE UPPER(c.title) LIKE UPPER(:title)")
    Optional<Coupon> findByTitle(String title);

    @Query("SELECT с FROM Coupon с")
    Optional<Collection<Coupon>> findAllCoupons();

    @Query("SELECT c FROM Coupon c WHERE c.amount > 0")
    Optional<Collection<Coupon>> findAllAvailableCoupons();

    @Modifying
    @Transactional
    @Query("DELETE FROM Coupon c WHERE c.endDate < CURRENT_DATE")
    void deleteExpiredCoupons();

    
    //   *****      for companies
    @Query("SELECT c FROM Coupon c WHERE c.company.id = :companyId AND c.id = :couponId")
    Optional<Coupon> findCompanyCoupon(int companyId, int couponId);

    @Query("SELECT c FROM Coupon c WHERE c.company.id = :companyId")
    Optional<Collection<Coupon>> findAllCompanyCoupons(int companyId);

    @Query("SELECT c FROM Coupon c WHERE c.company.id = :companyId AND c.category = :category")
    Optional<Collection<Coupon>> findAllCompanyCouponsByCategory(int companyId, Category category);

    @Query("SELECT c FROM Coupon c WHERE c.company.id = :companyId AND c.price <= :price")
    Optional<Collection<Coupon>> findAllCompanyCouponsByMaxPrice(int companyId, double price);


    
    //   *****      for customer
    @Query("SELECT coupon FROM Customer c JOIN c.coupons coupon WHERE c.id = :customerId AND coupon.id = :couponId")
    Optional<Coupon> findCustomerCoupon(int customerId, int couponId);

    @Query("SELECT coupon FROM Customer c JOIN c.coupons coupon WHERE c.id = :customerId")
    Optional<Collection<Coupon>> findAllCustomerCoupons(int customerId);

    @Query("SELECT coupon FROM Customer c JOIN c.coupons coupon WHERE c.id = :customerId AND coupon.category = :category")
    Optional<Collection<Coupon>> findAllCustomerCouponsByType(int customerId, Category category);

    @Query("SELECT coupon FROM Customer c JOIN c.coupons coupon WHERE c.id = :customerId AND coupon.price <= :price")
    Optional<Collection<Coupon>> findAllCustomerCouponsByPrice(int customerId, double price);

}
