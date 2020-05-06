package org.cap.customermgt.service;

import org.cap.customermgt.dao.ICustomerDao;
import org.cap.customermgt.entities.Customer;
import org.cap.customermgt.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private ICustomerDao dao;

    @Override
    public Customer save(Customer customer){
        customer=dao.save(customer);
        return customer;
    }

    @Override
    public Customer findById(int id){
        Optional<Customer> optional=dao.findById(id);
        if(optional.isPresent()) {
            Customer customer=optional.get();
            return customer;
        }
        throw new CustomerNotFoundException("customer not found for id="+id);

    }

    @Override
    public List<Customer> fetchAll() {
        List<Customer> list=dao.findAll();
        return list;
    }
}
