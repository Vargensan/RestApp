package com.rest.domain.device.control;

import javax.ejb.Asynchronous;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.rest.client.NotifierClient;

@Singleton
public class NotificationService {
	
	@Inject
	private NotifierClient notifierClient;
	
	@Inject
	private Logger logger;
	
	private int counter = 0;
	
	@Lock(LockType.READ)
	@Asynchronous
	public void notifySubsystems(String message) {
		logger.info("Notifying");
		try {
			int tmp = counter;
			while(tmp < counter + 10) {
				notifierClient.notifySubsystems(createMessage(message + " -- tmp"));
				Thread.sleep(1000);
				tmp++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String createMessage(String message) {
		return "|message: " + message + ", counter: " + counter + "|";
	}
	
}
