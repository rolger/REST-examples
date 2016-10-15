package at.allianz.restserver.vending;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import at.allianz.restserver.vending.domain.Beverage;
import at.allianz.restserver.vending.domain.VendingMachine;

/**
 * Root resource (exposed at "vending" path)
 */
@Path("/vending")
public class VendingMachineService {

	@GET
	@Path("/ping")
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld() {
		return "say hallo: http://localhost:8080/vending/vendingmachines/";
	}

	@GET
	@Path("/vendingmachines")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Collection<VendingMachine> readAllVendingMachines() {
		Collection<VendingMachine> allVendingMachines = VendingMachineRepository.getInstance().getAllVendingMachines();
		return new ArrayList<VendingMachine>(allVendingMachines);
	}

	@GET
	@Path("/vendingmachines/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public VendingMachine readMachine(@PathParam("id") String machineId) {
		VendingMachine machine = VendingMachineRepository.getInstance().getById(machineId);
		return machine;
	}

	@GET
	@Path("/vendingmachines/{machineId}/beverages/{beverageId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Beverage readBeverage(@PathParam("machineId") String machineId, @PathParam("beverageId") String beverageId) {
		VendingMachine machine = VendingMachineRepository.getInstance().getById(machineId);

		Beverage result = null;
		for (Beverage beverage : machine.getBeverages()) {
			if (beverage.getId().equals(beverageId)) {
				result = beverage;
				break;
			}
		}

		return result;
	}

}
