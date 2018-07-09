package com.rest.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.api.notifier.MessageDTO;
import com.infrastructure.providers.ManagementClient;

public class NotifierClient {

	private static final String NOTIFIER_SUBSYSTEM_NAME = "notifier-core";
	private static final String MESSAGE_PATH = "/message";

	public Response notifySubsystems(String message) {
		MessageDTO messageEntity = createMessageToSend(message);
		return createWebTarget().path(MESSAGE_PATH)
				.request()
				.post(Entity.entity(messageEntity, MediaType.APPLICATION_JSON));
	}

	private MessageDTO createMessageToSend(String message) {
		MessageDTO messageEntity = new MessageDTO();
		messageEntity.setValue(message);
		return messageEntity;
	}

	private WebTarget createWebTarget() {
		return ManagementClient.createWebTarget(NOTIFIER_SUBSYSTEM_NAME);
	}
	
}
