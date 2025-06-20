// Customer.java
// JPA Entity für Kunden-Datenbanktabelle
package com.cpb.customerdemo;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Standard-Konstruktor und Getter/Setter
    public Customer() {}
    public Customer(String name) { this.name = name; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
