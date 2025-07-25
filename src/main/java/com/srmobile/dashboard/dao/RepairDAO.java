package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Repair;

import java.util.List;

public interface RepairDAO {
    public Repair save(Repair repair);
    public Repair changeStatus(Long id, Repair.RepairStatus status);
    public List<Repair> findAll();
    public Repair findById(Long id);


}
