package com.example.CouponsExample.Component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.CouponsExample.Entities.Repo.CouponRepo;

//Build a daily Job to determine coupon validity

@Component
// Singleton
public class CouponExpirationDailyJob {

	CouponRepo coupRep;

	// removing all the duplicated coupons every 5 minutes
	// 300,000 Mill seconds equals to 5 minutes
	@Scheduled(fixedRate = 300000)
	public void scheduleTaskWithFixedRate() {
		coupRep.deleteExpiredCoupons();

	}

}
