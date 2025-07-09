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
    public List<Bill> getLastWeekRepairBills();
    public List<Bill> getLastMonthRepairBills();
    public List<Bill> getLastYearRepairBills();
    public List<Bill> getLastWeekItemsBills();
    public List<Bill> getLastMonthItemsBills();
    public List<Bill> getLastYearItemsBills();
    public BigDecimal getLastWeekItemBillProfitTotal();
    public BigDecimal getLastMonthItemBillProfitTotal();
    public BigDecimal getLastYearItemBillProfitTotal();
    public BigDecimal getLastWeekRepairBillProfitTotal();
    public BigDecimal getLastMonthRepairBillProfitTotal();
    public BigDecimal getLastYearRepairBillProfitTotal();
}
