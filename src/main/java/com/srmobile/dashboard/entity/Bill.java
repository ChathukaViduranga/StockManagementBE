package com.srmobile.dashboard.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;

    /* ──────────  Relationships  ────────── */

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(optional = true)             // null if this bill is for a repair
    @JoinColumn(name = "item_id")
    private Items item;

    @ManyToOne(optional = true)             // null if this bill is for an item sale
    @JoinColumn(name = "repair_id")
    private Repair repair;

    /* ──────────  Data columns  ────────── */

    @Column(name = "bill_date", nullable = false)
    private LocalDateTime billDate = LocalDateTime.now();

    @Column(name = "salesman_id", length = 10)
    private String salesmanId;              // map to Salesman entity later

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(precision = 10, scale = 2)
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(name = "net_amount", precision = 10, scale = 2)
    private BigDecimal netAmount;

    @Column(precision = 10, scale = 2)
    private BigDecimal profit;

    public Bill(){}
    public Bill(Customer customer, Items item, String salesmanId,
                BigDecimal price, BigDecimal discount,
                BigDecimal netAmount,BigDecimal profit) {
        this.customer    = customer;
        this.item        = item;
        this.salesmanId  = salesmanId;
        this.price       = price;
        this.discount    = discount;
        this.netAmount   = netAmount;
        this.profit     = profit;
    }

    public Bill(Customer customer, Repair repair, String salesmanId,
                BigDecimal price, BigDecimal discount,
                BigDecimal netAmount,BigDecimal profit) {
        this.customer    = customer;
        this.repair      = repair;
        this.salesmanId  = salesmanId;
        this.price       = price;
        this.discount    = discount;
        this.netAmount   = netAmount;
        this.profit      = profit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime billDate) {
        this.billDate = billDate;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", customer=" + customer +
                ", item=" + item +
                ", repair=" + repair +
                ", billDate=" + billDate +
                ", salesmanId='" + salesmanId + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", netAmount=" + netAmount +
                ", profit=" + profit +
                '}';
    }
}
