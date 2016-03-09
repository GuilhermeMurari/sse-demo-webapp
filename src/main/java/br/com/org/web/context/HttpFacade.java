package br.com.org.web.context;

import br.com.org.web.conf.JsonReader;
import br.com.org.web.conf.JsonWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_XML;

public class HttpFacade {

    private static final Client CLIENT = ClientBuilder.newClient()
            .register(JsonWriter.class)
            .register(JsonReader.class);

    private final WebTarget target;

    public HttpFacade(String baseUrl) {
        this.target = CLIENT.target(baseUrl);
    }

    private static String readResponse(WebApplicationException exception) {
        try {
            return exception.getResponse().readEntity(String.class);
        } catch (RuntimeException e) {
            return exception.toString();
        }
    }

    public <T> T get(String path, QueryParam query, Class<T> entity) {
        WebTarget target = query.append(this.target.path(path));
        Builder request = target.request(MediaType.APPLICATION_JSON);

        try {
            return request.get(entity);
        } catch (WebApplicationException exception) {
            throw new RuntimeException(readResponse(exception), exception);
        }
    }

    public <T, E> T put(String path, E payload, Class<T> entity) {
        WebTarget target = this.target.path(path);

        Builder request = target.request(APPLICATION_JSON);

        Entity<E> e = Entity.entity(payload, APPLICATION_JSON);

        try {
            Response response = request.put(e);
            return response.readEntity(entity);
        } catch (WebApplicationException exception) {
            throw new RuntimeException(readResponse(exception), exception);
        }
    }

}
