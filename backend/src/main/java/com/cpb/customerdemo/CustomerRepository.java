// CustomerRepository.java
// Spring Data JPA Repository für CRUD-Operationen auf Kunden
package com.cpb.customerdemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
