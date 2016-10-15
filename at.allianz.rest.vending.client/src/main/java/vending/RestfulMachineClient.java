package vending;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cedarsoftware.util.io.JsonWriter;

import vending.domain.VendingMachine;

public class RestfulMachineClient {
	private static final String VENDINGMACHINES = "vendingmachines/";

	private WebTarget vendingWebTarget;

	public RestfulMachineClient() {
		Client client = ClientBuilder.newClient();
		vendingWebTarget = client.target("http://localhost:8080/vending/");
	}

	public VendingMachine read(String machineId) {
		Response response = vendingWebTarget //
				.path(VENDINGMACHINES + machineId) //
				.request(MediaType.APPLICATION_JSON) //
				.get();

		VendingMachine machine = response.readEntity(VendingMachine.class);
		return machine;
	}

	public String readAll(String mediaType) {
		return readAll(mediaType, true);
	}

	public String readAll(String mediaType, boolean bFormat) {
		Builder readAll = vendingWebTarget //
				.path(VENDINGMACHINES) //
				.request(mediaType);

		String result = readAll.get(String.class);
		if (mediaType.equals(MediaType.APPLICATION_JSON) && bFormat) {
			result = JsonWriter.formatJson(result);
		}
		return result;
	}

	public void update(VendingMachine machine) {
		Response response = vendingWebTarget //
				.path(VENDINGMACHINES + machine.getId()) //
				.request(MediaType.APPLICATION_JSON) //
				.put(Entity.entity(machine, MediaType.APPLICATION_JSON));

		if (!response.getStatusInfo().equals(Status.OK)) {
			throw new RuntimeException("Could not post" + response);
		}
	}

}
