package com.rest.infrastructure;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;

import com.rest.domain.device.entity.DeviceEntity;

public class LogInterceptor {

	@Inject
	private Logger logger;
	
	@AroundInvoke
	public Object modifyGreeting(InvocationContext ctx) throws Exception {
	    Object[] parameters = ctx.getParameters();
	    DeviceEntity param = (DeviceEntity) parameters[0];
	    logger.info("interceptor: " + param);
	    return ctx.proceed();
	}

}
