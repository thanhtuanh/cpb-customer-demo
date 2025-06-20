package com.cpb.customerdemo;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionRepository transactionRepo;
    private final CustomerRepository customerRepo;

    public TransactionController(TransactionRepository transactionRepo, CustomerRepository customerRepo) {
        this.transactionRepo = transactionRepo;
        this.customerRepo = customerRepo;
    }

    // Transaktion für Kunden anlegen
    @PostMapping
    public Transaction addTransaction(@RequestBody TransactionRequest req) {
        Customer customer = customerRepo.findById(req.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Transaction transaction = new Transaction(req.amount(), req.description(), customer);
        return transactionRepo.save(transaction);
    }

    // Alle Transaktionen für einen Kunden anzeigen
    @GetMapping("/customer/{customerId}")
    public List<Transaction> getByCustomer(@PathVariable Long customerId) {
        return transactionRepo.findByCustomerId(customerId);
    }
}

// Hilfsklasse für Request-Body
record TransactionRequest(Long customerId, BigDecimal amount, String description) {}