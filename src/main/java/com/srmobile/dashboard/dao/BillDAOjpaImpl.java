package com.srmobile.dashboard.dao;

import com.srmobile.dashboard.entity.Bill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BillDAOjpaImpl implements BillDAO{
    EntityManager entityManager;
    public BillDAOjpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    private BigDecimal sumProfitBetween(LocalDate start, LocalDate endInclusive) {
        TypedQuery<BigDecimal> q = entityManager.createQuery(
                "SELECT COALESCE(SUM(b.profit), 0) " +
                        "FROM   Bill b " +
                        "WHERE  b.billDate BETWEEN :start AND :end",
                BigDecimal.class
        );
        // start at 00:00 of first day, end at 00:00 *tomorrow*
        q.setParameter("start", start.atStartOfDay());
        q.setParameter("end",   endInclusive.plusDays(1).atStartOfDay());

        return q.getSingleResult();
    }
    private List<Bill> listRepairBillsBetween(LocalDate start, LocalDate endIncl) {
        TypedQuery<Bill> q = entityManager.createQuery(
                "FROM Bill b " +
                        "WHERE b.repair IS NOT NULL " +          // ← only repairs
                        "AND   b.billDate BETWEEN :start AND :end",
                Bill.class
        );
        q.setParameter("start", start.atStartOfDay());
        q.setParameter("end",   endIncl.plusDays(1).atStartOfDay());
        return q.getResultList();
    }

    private List<Bill> listItemBillsBetween(LocalDate start, LocalDate endIncl) {
        TypedQuery<Bill> q = entityManager.createQuery(
                "FROM Bill b " +
                        "WHERE b.item IS NOT NULL " +            // ← only items
                        "AND   b.billDate BETWEEN :start AND :end",
                Bill.class
        );
        q.setParameter("start", start.atStartOfDay());
        q.setParameter("end",   endIncl.plusDays(1).atStartOfDay());
        return q.getResultList();
    }
    private BigDecimal sumItemProfitBetween(LocalDate start, LocalDate endIncl) {
        TypedQuery<BigDecimal> q = entityManager.createQuery(
                "SELECT COALESCE(SUM(b.profit),0) " +
                        "FROM   Bill b " +
                        "WHERE  b.billDate BETWEEN :start AND :end " +
                        "AND    b.item IS NOT NULL",
                BigDecimal.class
        );
        q.setParameter("start", start.atStartOfDay());
        q.setParameter("end",   endIncl.plusDays(1).atStartOfDay());
        return q.getSingleResult();
    }

    private BigDecimal sumRepairProfitBetween(LocalDate start, LocalDate endIncl) {
        TypedQuery<BigDecimal> q = entityManager.createQuery(
                "SELECT COALESCE(SUM(b.profit),0) " +
                        "FROM   Bill b " +
                        "WHERE  b.billDate BETWEEN :start AND :end " +
                        "AND    b.repair IS NOT NULL",
                BigDecimal.class
        );
        q.setParameter("start", start.atStartOfDay());
        q.setParameter("end",   endIncl.plusDays(1).atStartOfDay());
        return q.getSingleResult();
    }


    @Override
    @Transactional
    public Bill save(Bill bill) {
        return entityManager.merge(bill);
    }

    @Override
    public List<Bill> findAll() {
        TypedQuery<Bill> query = entityManager.createQuery("FROM Bill", Bill.class);
        return query.getResultList();
    }

    @Override
    public BigDecimal[] getWeeklyRevenue() {

        // ── 1.  Calculate date window ───────────────────────────────
        LocalDate today  = LocalDate.now();        // e.g. 2025-07-06
        LocalDate start  = today.minusDays(6);     // e.g. 2025-06-30

        // ── 2.  Query:  date(billDate) , SUM(profit)  ───────────────
        //    MySQL’s DATE() is exposed to JPQL with function('date', …)
        TypedQuery<Object[]> q = entityManager.createQuery(
                "SELECT function('date', b.billDate), SUM(b.profit) " +
                        "FROM   Bill b " +
                        "WHERE  b.billDate BETWEEN :start AND :end " +
                        "GROUP  BY function('date', b.billDate)",
                Object[].class
        );
        q.setParameter("start", start.atStartOfDay());            // 00:00 at start date
        q.setParameter("end",  today.plusDays(1).atStartOfDay()); // 00:00 tomorrow

        List<Object[]> rows = q.getResultList();   // each row → [java.sql.Date, BigDecimal]

        // ── 3.  Map results by LocalDate for quick lookup ───────────
        Map<LocalDate, BigDecimal> daily = new HashMap<>();
        for (Object[] r : rows) {
            LocalDate d = ((java.sql.Date) r[0]).toLocalDate();
            daily.put(d, (BigDecimal) r[1]);
        }

        // ── 4.  Build the 7-element array in chronological order ────
        BigDecimal[] profit7 = new BigDecimal[7];
        for (int i = 0; i < 7; i++) {
            LocalDate d = start.plusDays(i);
            profit7[i] = daily.getOrDefault(d, BigDecimal.ZERO);
        }
        return profit7;
    }

    private LocalDate[] windowWeek()  { return new LocalDate[]{ LocalDate.now().minusDays(6), LocalDate.now() }; }
    private LocalDate[] windowMonth() { return new LocalDate[]{ LocalDate.now().minusDays(29), LocalDate.now() }; }
    private LocalDate[] windowYear()  { return new LocalDate[]{ LocalDate.now().minusDays(364), LocalDate.now() }; }


    @Override
    public BigDecimal getLastWeekRevenue() {
        LocalDate today  = LocalDate.now();
        LocalDate start  = today.minusDays(6);

        return sumProfitBetween(start, today);
    }

    @Override
    public BigDecimal getLastMonthRevenue() {
        LocalDate today  = LocalDate.now();
        LocalDate start  = today.minusDays(29);

        return sumProfitBetween(start, today);
    }

    @Override
    public BigDecimal getLastYearRevenue() {
        LocalDate today  = LocalDate.now();
        LocalDate start  = today.minusDays(364);

        return sumProfitBetween(start, today);
    }

    @Override
    public List<Bill> getLastWeekRepairBills() {
        LocalDate today = LocalDate.now();
        return listRepairBillsBetween(today.minusDays(6), today);
    }

    @Override
    public List<Bill> getLastMonthRepairBills() {
        LocalDate today = LocalDate.now();
        return listRepairBillsBetween(today.minusDays(29), today);
    }

    @Override
    public List<Bill> getLastYearRepairBills() {
        LocalDate today = LocalDate.now();
        return listRepairBillsBetween(today.minusDays(364), today);
    }

    @Override
    public List<Bill> getLastWeekItemsBills() {
        LocalDate today = LocalDate.now();
        return listItemBillsBetween(today.minusDays(6), today);
    }

    @Override
    public List<Bill> getLastMonthItemsBills() {
        LocalDate today = LocalDate.now();
        return listItemBillsBetween(today.minusDays(29), today);
    }

    @Override
    public List<Bill> getLastYearItemsBills() {
        LocalDate today = LocalDate.now();
        return listItemBillsBetween(today.minusDays(364), today);
    }

    @Override
    public BigDecimal getLastWeekItemBillProfitTotal() {
        LocalDate[] w = windowWeek();
        return sumItemProfitBetween(w[0], w[1]);
    }

    @Override
    public BigDecimal getLastMonthItemBillProfitTotal() {
        LocalDate[] w = windowMonth();
        return sumItemProfitBetween(w[0], w[1]);
    }

    @Override
    public BigDecimal getLastYearItemBillProfitTotal() {
        LocalDate[] w = windowYear();
        return sumItemProfitBetween(w[0], w[1]);
    }

    @Override
    public BigDecimal getLastWeekRepairBillProfitTotal() {
        LocalDate[] w = windowWeek();
        return sumRepairProfitBetween(w[0], w[1]);
    }

    @Override
    public BigDecimal getLastMonthRepairBillProfitTotal() {
        LocalDate[] w = windowMonth();
        return sumRepairProfitBetween(w[0], w[1]);
    }

    @Override
    public BigDecimal getLastYearRepairBillProfitTotal() {
        LocalDate[] w = windowYear();
        return sumRepairProfitBetween(w[0], w[1]);
    }
}
