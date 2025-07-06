package com.srmobile.dashboard.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // MySQL AUTO_INCREMENT
    @Column(name = "expense_id")
    private Long id;

    /* ──────────  Data columns  ────────── */

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;          // e.g. 1520.75

    @Lob                                       // allows large text
    private String description;                // what the money was for

    @Column(length = 50)
    private String category;

    @Column(name = "expense_date", nullable = false)
    private LocalDate date = LocalDate.now();    // e.g. “Rent”, “Utilities”

    /* ──────────  Constructors  ────────── */

    public Expense() { }

    public Expense(BigDecimal amount, String description,
                   String category, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", date=" + date +
                '}';
    }
}
