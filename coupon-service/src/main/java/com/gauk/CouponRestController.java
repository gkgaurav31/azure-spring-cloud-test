package com.gauk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/coupon-api")
public class CouponRestController {

	@Autowired
	CouponRepository couponRepository;

	@Autowired
	Environment environment;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getPort() {
		return "COUPON SERVICE PORT: " + environment.getProperty("local.server.port");
	}

	@RequestMapping(value = "/coupon/{code}")
	@HystrixCommand(fallbackMethod = "fallBackMethod")
	public Coupon getCouponById(@PathVariable("code") String code) {
		return couponRepository.getCouponByCouponCode(code);
	}
	
	public Coupon fallBackMethod(String code) {
		//This method is the fallback for getCouponById() method
		System.out.println("Something went wrong.. in fall back of getCouponById..");
		return new Coupon();
}

	@RequestMapping(value = "/create")
	public Coupon createCoupon(@RequestBody Coupon coupon) {
		return couponRepository.save(coupon);
	}
}
