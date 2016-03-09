package br.com.org.domain;

public class Customer {

    private int customerId;
    private int storeId;
    private String name;

    public Customer(int customerId, int storeId, String name) {
        this.setCustomerId(customerId);
        this.setStoreId(storeId);
        this.setName(name);
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}