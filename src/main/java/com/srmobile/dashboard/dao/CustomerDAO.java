package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer save(Customer customer);
}
