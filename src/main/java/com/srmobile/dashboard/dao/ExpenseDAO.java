package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Expense;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseDAO {
    List<Expense> findAll();
    Expense save(Expense expense);
    BigDecimal getLastWeekExpense();
    BigDecimal getLastMonthExpense();
    BigDecimal getLastYearExpense();

}
