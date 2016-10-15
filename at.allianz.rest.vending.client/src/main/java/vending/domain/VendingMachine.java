package vending.domain;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachine {
	private String id;
	private String location;
	private BigDecimal balance;
	private BigDecimal cash;

	private List<Beverage> beverages;

	public VendingMachine() {
		super();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public List<Beverage> getBeverages() {
		return beverages;
	}

	public void setBeverages(List<Beverage> beverages) {
		this.beverages = beverages;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
