package br.com.org.web.conf;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    
    @Override
    public Response toResponse(WebApplicationException exception) {
        return Response.status(exception.getResponse().getStatus())
                .entity(exception.getMessage())
                .type(MediaType.APPLICATION_JSON_TYPE + ";charset=UTF-8")
                .encoding("UTF-8")
                .build();
    }
    
}
