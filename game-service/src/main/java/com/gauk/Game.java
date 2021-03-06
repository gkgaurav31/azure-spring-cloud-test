package com.gauk;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String gameName;
	private int price;
	
	@Transient
	private String code; //This will not be saved to the database. Required because we will be sending HTTP POST method with JSON body which contains Coupon Code. The JSON will be serialized to a Game Object.
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}

