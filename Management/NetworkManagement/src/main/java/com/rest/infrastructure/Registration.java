package com.rest.infrastructure;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;

@Singleton
@Startup
public class Registration {

	@Inject
	private Logger logger;
	
	@PostConstruct
	public void registerService() {
		String property = System.getProperty("mainServer");
		logger.info("mainServer: " + property);
	}
	 
	@PreDestroy
	public void unregisterService() {
		logger.info("closing");
	}
	 
}
