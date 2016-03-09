package br.com.org.web.conf;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(Throwable exception) {

        String uri = this.request.getRequestURI();
        String query = queryValue(this.request.getQueryString(), "");
        String method = this.request.getMethod();

        ErrorJson json = buildJsonError(exception);

        return Response.status(Status.INTERNAL_SERVER_ERROR)
                .entity(json)
                .type(MediaType.APPLICATION_JSON_TYPE + ";charset=UTF-8")
                .encoding("UTF-8")
                .build();
    }

    private ErrorJson buildJsonError(Throwable exception) {
        ErrorJson json = new ErrorJson();
        String[] messageSplited = exception.getMessage().split(":");

        if (messageSplited.length > 1) {
            json.setMessage(messageSplited[1]);
        } else {
            json.setMessage(exception.getMessage());
        }

        return json;
    }

    private String queryValue(String value, String defaultValue) {
        return value == null ? defaultValue : "?" + value;
    }

}
