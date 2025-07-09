package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOjpaImpl implements CustomerDAO{
    private  EntityManager entityManager;

    public CustomerDAOjpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query =
                entityManager.createQuery("FROM Customer", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public Customer findByContactNumber(String mobile) {
        try {
            // `contactNo` is the field name in the Customer entity
            // change the JPQL property name if your entity uses a different one
            TypedQuery<Customer> q = entityManager.createQuery(
                    "FROM Customer c WHERE c.contactNo = :mobile",
                    Customer.class
            );
            q.setParameter("mobile", mobile);
            return q.getSingleResult();
        } catch (jakarta.persistence.NoResultException ex) {
            // no customer with that number → return null instead of throwing
            return null;
        }
    }


}
