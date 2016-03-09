package br.com.org.repository;

import br.com.org.domain.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guilherme.murari on 09/03/16.
 */
public class CustomerRepository {

    public static List<Customer> getCustomers() {
        ArrayList<Customer> list  = new ArrayList<>();
        list.add(new Customer(1, 100, "Lorem Ipsum"));
        list.add(new Customer(2, 100, "Xablau"));
        list.add(new Customer(3, 100, "Foobar"));

        return list;
    }
}