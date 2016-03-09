package br.com.org.web.conf;

import flexjson.JSON;

class ErrorJson {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @JSON
    public String getMessage() {
        return message;
    }
}
