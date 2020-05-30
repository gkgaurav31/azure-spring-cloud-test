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
@RequestMapping(value = "/game-api")
public class GameRestController {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	Environment environment;

	//Feign Client - COUPON-SERVICE
	@Autowired
	CouponClient couponClient;	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getPort() {
		return "GAME SERVICE PORT: " + environment.getProperty("local.server.port");
	}

	@RequestMapping(value = "/create")
	@HystrixCommand(fallbackMethod = "sendErrorResponse")
	public Game createGame(@RequestBody Game game) {

		//Get Discount from Coupon Micro-Service:
		Coupon coupon = couponClient.getCoupon(game.getCode());
		int discount = coupon.getDiscount();
		game.setPrice(game.getPrice() - discount);

		return gameRepository.save(game);
	}

	public Game sendErrorResponse(Game game) {
		//This is the fallback method for createGame Method which has a dependency on Coupon Service. 
		//So, if the coupon service fails, we will handle it by this method
		System.out.println("Something went wrong.. in fallback method..");
		return new Game();
	}

	@RequestMapping(value = "/game/{id}")
	public Game getGameById(@PathVariable("id") int id) {
		return gameRepository.getGameById(id);
	}

}
