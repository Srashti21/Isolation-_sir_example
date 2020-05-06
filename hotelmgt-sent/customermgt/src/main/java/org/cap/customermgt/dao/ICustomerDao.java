package org.cap.customermgt.dao;

import org.cap.customermgt.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICustomerDao extends JpaRepository<Customer,Integer> {
}
