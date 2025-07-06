package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Repair;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RepairDAOImpl implements RepairDAO{
    private EntityManager entityManager;
    public RepairDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Repair save(Repair repair) {
        return entityManager.merge(repair);
    }

    @Override
    @Transactional
    public Repair changeStatus(Long id, Repair.RepairStatus status) {
        Repair repair = entityManager.find(Repair.class, id);
        repair.setStatus(status);
        return entityManager.merge(repair);
    }


    @Override
    public List<Repair> findAll() {
        TypedQuery<Repair> query = entityManager.createQuery("FROM Repair", Repair.class);

        return query.getResultList();
    }

    @Override
    public Repair findById(Long id) {
        Repair repair = entityManager.find(Repair.class, id);
        return repair;
    }
}
