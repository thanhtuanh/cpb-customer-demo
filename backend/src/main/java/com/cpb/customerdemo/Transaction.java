package com.cpb.customerdemo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// SpotBugs: EI_EXPOSE_REP ist bei JPA-Entities akzeptabel, da Customer absichtlich als Entity referenziert wird.
@SuppressWarnings("EI_EXPOSE_REP")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String description;
    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne
    private Customer customer;

    public Transaction() {}

    public Transaction(BigDecimal amount, String description, Customer customer) {
        this.amount = amount;
        this.description = description;
        this.customer = customer;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public BigDecimal getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDateTime getTimestamp() { return timestamp; }

    // Direkte Referenz auf Customer ist im JPA-Kontext gewollt.
    public Customer getCustomer() { return customer; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setDescription(String description) { this.description = description; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}