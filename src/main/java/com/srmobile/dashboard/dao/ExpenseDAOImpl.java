package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Expense;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ExpenseDAOImpl implements ExpenseDAO{
    private EntityManager entityManager;
    public ExpenseDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private BigDecimal sumAmountBetween(LocalDate start, LocalDate endInclusive) {
        TypedQuery<BigDecimal> q = entityManager.createQuery(
                "SELECT COALESCE(SUM(e.amount), 0) " +
                        "FROM   Expense e " +
                        "WHERE  e.date BETWEEN :start AND :end",
                BigDecimal.class
        );
        q.setParameter("start", start);          // LocalDate
        q.setParameter("end",   endInclusive);   // LocalDate  (inclusive)

        return q.getSingleResult();
    }

    @Override
    public List<Expense> findAll() {
        TypedQuery<Expense> query =
                entityManager.createQuery("FROM Expense", Expense.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Expense save(Expense expense) {
        return entityManager.merge(expense);
    }

    @Override
    public BigDecimal getLastWeekExpense() {
        LocalDate today = LocalDate.now();
        LocalDate start = today.minusDays(6);

        return sumAmountBetween(start, today);
    }

    @Override
    public BigDecimal getLastMonthExpense() {
        LocalDate today = LocalDate.now();
        LocalDate start = today.minusDays(29);

        return sumAmountBetween(start, today);
    }

    @Override
    public BigDecimal getLastYearExpense() {
        LocalDate today = LocalDate.now();
        LocalDate start = today.minusDays(364);

        return sumAmountBetween(start, today);
    }
}
