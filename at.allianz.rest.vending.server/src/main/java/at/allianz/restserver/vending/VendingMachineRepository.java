package at.allianz.restserver.vending;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import at.allianz.restserver.vending.domain.Beverage;
import at.allianz.restserver.vending.domain.VendingMachine;

public class VendingMachineRepository {

	private static final VendingMachineRepository INSTANCE = new VendingMachineRepository();

	private Map<String, VendingMachine> vendingMachines = new ConcurrentHashMap<>();

	private VendingMachineRepository() {
		addMachine("AMOS BT1 3.Stock West", "1");
		addMachine("AMOS BT1 3.Stock Ost", "2");
		addMachine("AMOS BT1 2.Stock West", "3");
		addMachine("AMOS BT1 2.Stock Ost", "4");
	}

	private void addMachine(String location, String id) {
		VendingMachine machine = new VendingMachine();
		machine.setId(id);
		machine.setBalance(BigDecimal.ZERO);
		machine.setCash(BigDecimal.ZERO);;
		machine.setLocation(location);
		machine.getBeverages().add(new Beverage("1", "Coca-Cola", new BigDecimal("1.80"), 10));
		machine.getBeverages().add(new Beverage("2", "Römerquelle", new BigDecimal("1.10"), 10));
		machine.getBeverages().add(new Beverage("3", "Sprite", new BigDecimal("1.80"), 10));
		vendingMachines.put(machine.getId(), machine);
	}

	public static VendingMachineRepository getInstance() {
		return INSTANCE;
	}

	public Collection<VendingMachine> getAllVendingMachines() {
		return vendingMachines.values();
	}

	public VendingMachine getById(String id) {
		return vendingMachines.get(id);
	}

	public void add(VendingMachine blogPost) {
		String id = String.valueOf(VendingMachineRepository.getInstance().getAllVendingMachines().size() + 1);
		blogPost.setId(id);
		vendingMachines.put(id, blogPost);
	}

	public void remove(VendingMachine blogPost) {
		vendingMachines.remove(blogPost.getId());
	}

}
