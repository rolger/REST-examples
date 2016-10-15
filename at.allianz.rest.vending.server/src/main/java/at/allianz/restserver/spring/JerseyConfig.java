package at.allianz.restserver.spring;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import at.allianz.restserver.vending.VendingMachineService;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(VendingMachineService.class);
	}

}