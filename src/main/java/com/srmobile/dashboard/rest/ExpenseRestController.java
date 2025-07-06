package com.srmobile.dashboard.rest;

import com.srmobile.dashboard.dao.ExpenseDAO;
import com.srmobile.dashboard.entity.Expense;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseRestController {
    ExpenseDAO expenseDAO;
    public ExpenseRestController(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(){
        return expenseDAO.findAll();
    }

    @PostMapping("/expenses")
    public Expense saveExpense(@RequestBody Expense expense){
        return expenseDAO.save(expense);
    }

    @GetMapping("/expenses/last-week")
    public BigDecimal getLastWeekExpense(){
        return expenseDAO.getLastWeekExpense();
    }
    @GetMapping("/expenses/last-month")
    public BigDecimal getLastMonthExpense(){
        return expenseDAO.getLastMonthExpense();
    }
    @GetMapping("/expenses/last-year")
    public BigDecimal getLastYearExpense(){
        return expenseDAO.getLastYearExpense();
    }

}
