package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Bill;


import java.math.BigDecimal;
import java.util.List;

public interface BillDAO {
    public Bill save(Bill bill);
    public List<Bill> findAll();
    public BigDecimal[] getWeeklyRevenue();
    public BigDecimal getLastWeekRevenue();
    public BigDecimal getLastMonthRevenue();
    public BigDecimal getLastYearRevenue();
}
