package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Items;

import java.util.List;

public interface ItemsDAO {
    List<Items> findAll();
    Items findById(String id);
    Items save(Items item);
    Items changeStatus(String id, Items.ItemStatus status);
    List<Items> findAvailable();
    Items getAvailableItemById(String id);
}
