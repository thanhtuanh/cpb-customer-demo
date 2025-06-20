// CustomerController.java
// REST-API für das Verwalten von Kunden
package com.cpb.customerdemo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository repo;

    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    // Gibt alle Kunden zurück
    @GetMapping
    public List<Customer> all() {
        return repo.findAll();
    }

    // Fügt einen neuen Kunden hinzu
    @PostMapping
    public Customer add(@RequestBody Customer customer) {
        return repo.save(customer);
    }
}
