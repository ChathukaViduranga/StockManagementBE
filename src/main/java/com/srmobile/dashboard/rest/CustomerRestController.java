package com.srmobile.dashboard.rest;

import com.srmobile.dashboard.dao.CustomerDAO;
import com.srmobile.dashboard.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerRestController {
    CustomerDAO customerDAO;
    public CustomerRestController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerDAO.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerDAO.findById(id);
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerDAO.save(customer);
    }
}
