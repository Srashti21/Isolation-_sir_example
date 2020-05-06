package org.cap.customermgt.service;

import org.cap.customermgt.entities.Customer;

import java.util.List;

public interface ICustomerService {

    Customer findById(int id);

    Customer save(Customer customer);

    List<Customer>fetchAll();
}
