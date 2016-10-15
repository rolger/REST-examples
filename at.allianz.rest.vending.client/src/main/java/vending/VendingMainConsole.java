package vending;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import vending.domain.Beverage;
import vending.domain.VendingMachine;

public class VendingMainConsole {

	private static final String SHOW_ALL = "1";
	private static final String BUY = "2";
	private static final String FILL = "3";
	private static final String NEW = "4";
	private static final String PRINT_BALANCE = "5";
	private static final String PRINT_CASH = "6";
	private static final String CLEAR_CASH = "7";

	public static void main(String[] args) throws IOException {
		VendingBusiness business = new VendingBusiness();

		while (true) {
			switch (showMenue()) {
			case "X":
				System.exit(0);
				break;

			case SHOW_ALL:
				business.showAllMachines();
				break;

			case BUY:
				business.buyBeverage(readAutomat());
				break;

			case FILL:
				business.fillBeverage(readAutomat());
				break;

			case NEW:
				business.newBeverage(readAutomat());
				break;

			case PRINT_BALANCE:
				business.printBalance();
				break;

			case PRINT_CASH:
				business.printCash(readAutomat());
				break;

			case CLEAR_CASH:
				business.clearCash(readAutomat());
				break;

			default:
				continue;
			}
		}
	}

	private static String showMenue() {
		System.out.println("Wählen sie eine Aktion");
		System.out.println(SHOW_ALL + " Alle Automaten anzeigen");
		System.out.println(BUY + " Kaufe ein Getränk");
		System.out.println(FILL + " Getränk nachfüllen");
		System.out.println(NEW + " Neues Getränk nachfüllen");
		System.out.println(PRINT_BALANCE + " Ausdruck verkaufte Getränke");
		System.out.println(PRINT_CASH + " Ausdruck Kassa");
		System.out.println(CLEAR_CASH + " Kassa leeren");
		System.out.println("X Exit");
		System.out.println();

		return readInput().toUpperCase();
	}

	public static String selectBeverage(String currentMachine, final VendingMachine machine) {
		for (final Beverage bev : machine.getBeverages()) {
			if (bev.getQuantity() > 0) {
				System.out.println(bev.getId() + " " + bev.getBrand());
			}
		}
		System.out.println("Wähle Getränk:");
		return readInput();
	}

	private static String readInput() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}
		return null;
	}

	private static String readAutomat() {
		System.out.println("Wähle Automat(1-4):");
		String currentMachine = readInput();
		return currentMachine;
	}

}
