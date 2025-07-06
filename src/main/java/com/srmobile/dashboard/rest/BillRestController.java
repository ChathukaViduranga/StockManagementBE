package com.srmobile.dashboard.rest;

import com.srmobile.dashboard.dao.BillDAO;
import com.srmobile.dashboard.entity.Bill;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class BillRestController {
    BillDAO billDAO;
    public BillRestController(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    @GetMapping("/bills")
    public List<Bill> getAllBills(){
        return billDAO.findAll();
    }

    @PostMapping("/bills")
    public Bill saveBill(@RequestBody Bill bill){
        return billDAO.save(bill);
    }

    @GetMapping("/bills/daily")
    public BigDecimal[] getDailyRevenue(){
        return billDAO.getWeeklyRevenue();
    }

    @GetMapping("/bills/last-week")
    public BigDecimal getLastWeekRevenue(){
        return billDAO.getLastWeekRevenue();
    }
    @GetMapping("/bills/last-month")
    public BigDecimal getLastMonthRevenue(){
        return billDAO.getLastMonthRevenue();
    }
    @GetMapping("/bills/last-year")
    public BigDecimal getLastYearRevenue(){
        return billDAO.getLastYearRevenue();
    }
}
