package vending;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import vending.domain.Beverage;

public class RestfulBeverageClient {
	private static final String BEVERAGES = "/beverages/";
	private static final String VENDINGMACHINES = "vendingmachines/";

	private WebTarget vendingWebTarget;

	public RestfulBeverageClient() {
		Client client = ClientBuilder.newClient();
		vendingWebTarget = client.target("http://localhost:8080/vending/");
	}

	public String create(String machineId, Beverage beverage) {
		MultivaluedMap<String, String> formParam = new MultivaluedHashMap<String, String>();
		formParam.add("brand", beverage.getBrand());
		formParam.add("price", beverage.getPrice().toPlainString());
		formParam.add("quantity", String.valueOf(beverage.getQuantity()));

		Response response = vendingWebTarget //
				.path(VENDINGMACHINES + machineId + BEVERAGES).request() //
				.post(Entity.form(formParam));

		String location = response.getHeaderString("Location");
		return location.substring(location.lastIndexOf("/") + 1);
	}

	public Beverage read(String machineId, String beverageId) {
		Response response = vendingWebTarget //
				.path(VENDINGMACHINES + machineId + BEVERAGES + beverageId)//
				.request(MediaType.APPLICATION_JSON) //
				.get();

		Beverage beverage = response.readEntity(Beverage.class);
		return beverage;
	}

	public void update(String machineId, Beverage beverage) {
		throw new RuntimeException("Not yet implemented");
	}

}
