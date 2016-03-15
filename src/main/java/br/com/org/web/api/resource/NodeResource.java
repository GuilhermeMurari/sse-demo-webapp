package br.com.org.web.api.resource;

/**
 * Created by guilherme.murari on 09/03/16.
 */

import br.com.org.domain.Customer;
import br.com.org.repository.CustomerRepository;
import br.com.org.web.context.HttpFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("node")
public class NodeResource {

    private static final String BASE_URL = "http://localhost:9090";
    private static final String NOTIFY_STORE_URI = "/push-event";


    @GET
    @Path("/notify")
    public String notifyServer() {
        HttpFacade httpClient = new HttpFacade(BASE_URL);

        httpClient.put(NOTIFY_STORE_URI, CustomerRepository.getCustomers(), Customer.class);
        return "CALLED NODE JS";
    }

}