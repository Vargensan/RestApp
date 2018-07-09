package com.rest.domain.notifier.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

import com.api.notifier.MessageDTO;

@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path(NotifierResource.MAIN_PATH)
public class NotifierResource {

	public static final String MAIN_PATH = "/message";
	
	@Inject
	private Logger logger;
	
	@POST
	public Response sendNotify(MessageDTO message) {
		logger.info("Received message: " + message.getValue());
		return Response.ok()
				.build();
	}
	
}
