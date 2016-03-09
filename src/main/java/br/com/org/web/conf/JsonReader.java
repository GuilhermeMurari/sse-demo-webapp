package br.com.org.web.conf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import flexjson.JSONDeserializer;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JsonReader implements MessageBodyReader<Object> {

    private static final String ERROR = "trying to deserialize to %s: %s";
    private static final int MAX_BYTES = 50000000;
    private static final JSONDeserializer<?> DESERIALIZER = new JSONDeserializer<>();
    
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        
        String entity = readEntity(entityStream);
        
        try {
            return DESERIALIZER.deserialize(entity, type);
        } catch (Exception e) {
            String error = String.format(ERROR, type.getName(), entity);
            throw new RuntimeException(error, e);
        }
    }

    private String readEntity(InputStream entityStream) throws IOException {
        BoundedInputStream boundedStream = new BoundedInputStream(entityStream, MAX_BYTES);
        
        InputStreamReader reader = new InputStreamReader(boundedStream, "UTF-8");
        BufferedReader breader = new BufferedReader(reader);
        
        String line = breader.readLine();
        StringBuilder builder = new StringBuilder();
        while (line != null) {
            builder.append(line).append("\n");
            line = breader.readLine();
        }
        
        breader.close();
        return builder.toString();
    }
    
}
