package com.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class NotifierApplication extends Application {
	
	public static final String APPLICATION_PATH = "/notifier";
	
}