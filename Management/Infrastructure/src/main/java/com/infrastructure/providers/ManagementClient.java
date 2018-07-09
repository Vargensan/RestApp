package com.infrastructure.providers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public final class ManagementClient {

	private static final String SERVER_ADDRESS = "http://localhost:8080";
	private static final String NOTIFIER_PATH = "/notifier";
	private static final String NETWORK_MANAGEMENT_PATH = "/network-management";

	private static final String NOTIFIER_SUBSYSTEM_NAME = "notifier-core";
	private static final String NETWORK_MANAGEMENT_SUBSYSTEM_NAME = "network-management-core";

	public static WebTarget createWebTarget(String serviceName) {
		Client client = ClientBuilder.newClient();
		if (NOTIFIER_SUBSYSTEM_NAME.equals(serviceName)) {
			return client.target(SERVER_ADDRESS + NOTIFIER_PATH);
		} else if (NETWORK_MANAGEMENT_SUBSYSTEM_NAME.equals(serviceName)) {
			return client.target(SERVER_ADDRESS + NETWORK_MANAGEMENT_PATH);
		}
		throw new UnsupportedOperationException("There is no subsystem with name: " + serviceName);
	}

}
