package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Items;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ItemsDAOjpaImpl implements ItemsDAO{
    private EntityManager entityManager;

    @Autowired
    public ItemsDAOjpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Items> findAll() {
        TypedQuery<Items> query = entityManager.createQuery("from Items", Items.class);
        List<Items> items = query.getResultList();
        return items;
    }

    @Override
    public Items findById(String id) {
        Items item = entityManager.find(Items.class, id);
        return item;
    }

    @Override
    @Transactional
    public Items save(Items item) {
        return entityManager.merge(item);
    }

    @Override
    @Transactional
    public Items changeStatus(String id, Items.ItemStatus status) {
        Items item = entityManager.find(Items.class, id);
        item.setStatus(status);
        entityManager.merge(item);
        return item;
    }

    @Override
    public List<Items> findAvailable() {
        TypedQuery<Items> q = entityManager.createQuery(
                "FROM Items i WHERE i.status = :status", Items.class);
        q.setParameter("status", Items.ItemStatus.AVAILABLE);
        return q.getResultList();
    }


}
