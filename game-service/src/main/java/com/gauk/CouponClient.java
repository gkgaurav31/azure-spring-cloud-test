package com.gauk;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("gateway")
public interface CouponClient {
	
	/*
	 * @RequestMapping(value = "/coupon-api/coupon/{code}") Coupon
	 * getCoupon(@PathVariable("code") String code);
	 */
	
	@RequestMapping(value = "/coupon-service/coupon-api/coupon/{code}") //Since we are going via Zuul API Gateway, the URL needs to be appended with the MicroService Name
	Coupon getCoupon(@PathVariable("code") String code);

	
}	