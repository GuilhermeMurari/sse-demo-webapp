package br.com.org.web.api;

/**
 * Created by guilherme.murari on 09/03/16.
 */

import javax.ws.rs.Path;

(@Path("node"))
public class NodeResource {

    @GET
    @Path("/status")
    public String status() {
        return "CALLED NODE JS";
    }

}
