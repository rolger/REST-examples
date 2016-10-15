package vending;

import java.math.BigDecimal;

import javax.ws.rs.core.MediaType;

import vending.domain.Beverage;
import vending.domain.VendingMachine;

public class VendingBusiness {
	private RestfulBeverageClient beverageClient;
	private RestfulMachineClient machineClient;

	public VendingBusiness() {
		beverageClient = new RestfulBeverageClient();
		machineClient = new RestfulMachineClient();
	}

	public void showAllMachines() {
		System.out.println(machineClient.readAll(MediaType.APPLICATION_JSON, false));
		System.out.println(machineClient.readAll(MediaType.APPLICATION_XML));
	}

	public void buyBeverage(String currentMachine) {
		final VendingMachine machine = machineClient.read(currentMachine);
		String beverageId = VendingMainConsole.selectBeverage(currentMachine, machine);
		final Beverage beverage = beverageClient.read(currentMachine, beverageId);
		
		beverage.setQuantity(beverage.getQuantity() - 1);
		beverageClient.update(currentMachine, beverage);

		BigDecimal newCash = machine.getCash().add(beverage.getPrice());
		machine.setCash(newCash);
		BigDecimal newBalance = machine.getBalance().add(beverage.getPrice());
		machine.setBalance(newBalance);

		machineClient.update(machine);
	}

	public void fillBeverage(String currentMachine) {
		final VendingMachine machine = machineClient.read(currentMachine);
		String beverageId = VendingMainConsole.selectBeverage(currentMachine, machine);
		final Beverage beverage = beverageClient.read(currentMachine, beverageId);
		
		beverage.setQuantity(beverage.getQuantity() + 1);
		beverageClient.update(currentMachine, beverage);
	}
	
	public void newBeverage(String currentMachine) {
		Beverage beverage = new Beverage();
		beverage.setBrand("Kalter Kaffee");

		beverage.setPrice(new BigDecimal("2.10"));
		beverage.setQuantity(10);
		String newId = beverageClient.create(currentMachine, beverage);
		
		Beverage newBeverage = beverageClient.read(currentMachine, newId);

		System.out.println("\nCreated new beverage:");
		System.out.println(newBeverage.getId() + " " + newBeverage.getBrand());
	}

	public void printBalance() {
		
	}

	public void printCash(String currentMachine) {
		
	}

	public void clearCash(String currentMachine) {
		
	}



}
