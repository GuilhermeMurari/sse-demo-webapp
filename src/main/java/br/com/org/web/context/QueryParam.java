package br.com.org.web.context;

import javax.ws.rs.client.WebTarget;
import java.util.HashMap;
import java.util.Map;

public class QueryParam {
    
    private final Map<String, Object[]> values;
    
    public QueryParam() {
        this.values = new HashMap<>();
    }

    public WebTarget append(WebTarget source) {
        WebTarget target = source;
        for (String key : values.keySet()) {
            target = target.queryParam(key, values.get(key));
        }
        return target;
    }
    
}
