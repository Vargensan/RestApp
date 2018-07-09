package com.rest.infrastructure;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {        
        // For simplicity I am preparing error xml by hand.
        // Ideally we should create an ErrorResponse class to hold the error info.
        StringBuilder response = new StringBuilder("<response>");
        response.append("<status>ERROR</status>");
        response.append("<message>" + e.getMessage() + "</message>");
        response.append("</response>");
        return Response.serverError().entity(response.toString()).type(MediaType.APPLICATION_XML).build();
    }

}