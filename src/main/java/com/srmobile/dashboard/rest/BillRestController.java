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

    @GetMapping("/bills/last-week-repairs")
    public List<Bill> getLastWeekRepairBills(){
        return billDAO.getLastWeekRepairBills();
    }
    @GetMapping("/bills/last-month-repairs")
    public List<Bill> getLastMonthRepairBills(){
        return billDAO.getLastMonthRepairBills();
    }
    @GetMapping("/bills/last-year-repairs")
    public  List<Bill> getLastYearRepairBills(){
        return billDAO.getLastYearRepairBills();
    }
    @GetMapping("/bills/last-week-items")
    public List<Bill> getLastWeekItemsBills(){
        return billDAO.getLastWeekItemsBills();
    }
    @GetMapping("/bills/last-month-items")
    public List<Bill> getLastMonthItemsBills(){
        return billDAO.getLastMonthItemsBills();
    }
    @GetMapping("/bills/last-year-items")
    public List<Bill> getLastYearItemsBills(){
        return billDAO.getLastYearItemsBills();
    }

    @GetMapping("/bills/last-week-item-profit")
    public BigDecimal getLastWeekItemBillProfitTotal(){
        return billDAO.getLastWeekItemBillProfitTotal();
    }
    @GetMapping("/bills/last-month-item-profit")
    public BigDecimal getLastMonthItemBillProfitTotal(){
        return billDAO.getLastMonthItemBillProfitTotal();
    }
    @GetMapping("/bills/last-year-item-profit")
    public BigDecimal getLastYearItemBillProfitTotal(){
        return billDAO.getLastYearItemBillProfitTotal();
    }

    @GetMapping("/bills/last-week-repair-profit")
    public BigDecimal getLastWeekRepairBillProfitTotal(){
        return billDAO.getLastWeekRepairBillProfitTotal();
    }
    @GetMapping("/bills/last-month-repair-profit")
    public BigDecimal getLastMonthRepairBillProfitTotal(){
        return billDAO.getLastMonthRepairBillProfitTotal();
    }

    @GetMapping("/bills/last-year-repair-profit")
    public BigDecimal getLastYearRepairBillProfitTotal(){
        return billDAO.getLastYearRepairBillProfitTotal();
    }

}
