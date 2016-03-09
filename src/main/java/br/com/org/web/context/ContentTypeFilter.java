package br.com.org.web.context;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

class ContentTypeFilter implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        MultivaluedMap<String, String> headers = responseContext.getHeaders();
        headers.putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML);
    }

}
