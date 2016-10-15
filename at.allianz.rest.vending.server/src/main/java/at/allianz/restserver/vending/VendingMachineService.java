package at.allianz.restserver.vending;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<VendingMachine> readAllVendingMachines() {
		Collection<VendingMachine> allVendingMachines = VendingMachineRepository.getInstance().getAllVendingMachines();
		return new ArrayList<VendingMachine>(allVendingMachines);
	}
}
