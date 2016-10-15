package vending.domain;

import java.math.BigDecimal;

public class Beverage {
	private String id;
	private String brand;
	private BigDecimal price;
	private int quantity;

	public Beverage() {
		super();
	}

	public Beverage(String id, String brand, BigDecimal price, int quantity) {
		super();
		this.id = id;
		this.brand = brand;
		this.price = price;
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
