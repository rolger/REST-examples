package at.allianz.restserver.vending;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		checkIfNotFound(machineId, machine);
		return machine;
	}

	@GET
	@Path("/vendingmachines/{machineId}/beverages/{beverageId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Beverage readBeveage(@PathParam("machineId") String machineId, @PathParam("beverageId") String beverageId) {
		VendingMachine machine = VendingMachineRepository.getInstance().getById(machineId);
		checkIfNotFound(machineId, machine);

		Beverage result = null;
		for (Beverage beverage : machine.getBeverages()) {
			if (beverage.getId().equals(beverageId)) {
				result = beverage;
				break;
			}
		}

		return result;
	}

	@PUT
	@Path("/vendingmachines/{machineId}/beverages/{beverageId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Beverage updateBeveage(@PathParam("machineId") String machineId, @PathParam("beverageId") String beverageId,
			@FormParam("brand") String brand, @FormParam("price") String price,
			@FormParam("quantity") String quantity) {
		VendingMachine machine = VendingMachineRepository.getInstance().getById(machineId);
		checkIfNotFound(machineId, machine);

		Beverage result = null;
		for (Beverage beverage : machine.getBeverages()) {
			if (beverage.getId().equals(beverageId)) {
				result = beverage;
				break;
			}
		}

		checkIfNotFound(beverageId, result);

		if (brand != null) {
			result.setBrand(brand);
		}
		if (price != null) {
			result.setPrice(new BigDecimal(price));
		}
		if (quantity != null) {
			result.setQuantity(Integer.valueOf(quantity));
		}

		return result;
	}

	private void checkIfNotFound(String beverageId, Beverage result) {
		if (result == null) {
			throw new WebApplicationException("Beverage not found for ID:" + beverageId, Response.Status.NOT_FOUND);
		}
	}

	private void checkIfNotFound(String machineId, VendingMachine machine) {
		if (machine == null) {
			throw new WebApplicationException("Machine not found for ID:" + machineId, Response.Status.NOT_FOUND);
		}
	}

	@PUT
	@Path("/vendingmachines/{machineId}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public VendingMachine updateMachine(@PathParam("machineId") String machineId, VendingMachine newMachine) {
		VendingMachine machine = VendingMachineRepository.getInstance().getById(machineId);
		checkIfNotFound(machineId, machine);

		machine.setBalance(newMachine.getBalance());
		machine.setCash(newMachine.getCash());

		return machine;
	}

}
