package com.srmobile.dashboard.entity;

import jakarta.persistence.*;

@Entity
@Table(name="repairs")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // MySQL AUTO_INCREMENT
    @Column(name = "repair_id")
    private Long id;

    /* ──────────  Relationships  ────────── */

    @ManyToOne(optional = false)                            // FK → customers.customer_id
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /* ──────────  Data columns  ────────── */

    @Column(name = "device_name", nullable = false, length = 100)
    private String deviceName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RepairStatus status = RepairStatus.Pending;

    public enum RepairStatus {
        Pending,InProgress,Completed,Delivered
    }

    public Repair(){}
    public Repair(Customer customer, String deviceName) {
        this.customer = customer;
        this.deviceName = deviceName;
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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public RepairStatus getStatus() {
        return status;
    }

    public void setStatus(RepairStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", customer=" + customer +
                ", deviceName='" + deviceName + '\'' +
                ", status=" + status +
                '}';
    }
}
