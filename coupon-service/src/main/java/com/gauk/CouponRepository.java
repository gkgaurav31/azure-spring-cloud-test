package com.gauk;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository  extends JpaRepository<Coupon, Long>{

	Coupon getCouponById(int id);
	Coupon getCouponByCouponCode(String couponCode);
}
