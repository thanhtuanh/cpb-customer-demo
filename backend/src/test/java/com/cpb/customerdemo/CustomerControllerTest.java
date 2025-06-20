package com.cpb.customerdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit Test für CustomerController mit Mockito
 */
class CustomerControllerTest {

    // Mock für das CustomerRepository
    private CustomerRepository customerRepository;
    // System under test
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        // Mockito-Mock für das Repository erzeugen
        customerRepository = mock(CustomerRepository.class);
        // Controller mit gemocktem Repository instanziieren
        customerController = new CustomerController(customerRepository);
    }

    @Test
    void testAll_ReturnsAllCustomers() {
        // Arrange: Zwei Beispielkunden vorbereiten
        Customer c1 = new Customer("Max Mustermann");
        Customer c2 = new Customer("Erika Musterfrau");
        List<Customer> mockList = Arrays.asList(c1, c2);

        // Verhalten des Mocks definieren: findAll() gibt mockList zurück
        when(customerRepository.findAll()).thenReturn(mockList);

        // Act: Methode aufrufen
        List<Customer> result = customerController.all();

        // Assert: Rückgabe prüfen
        assertEquals(2, result.size());
        assertTrue(result.contains(c1));
        assertTrue(result.contains(c2));
        // Überprüfen, dass findAll() genau einmal aufgerufen wurde
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testAdd_SavesCustomer() {
        // Arrange: Beispielkunde wird erstellt
        Customer c = new Customer("Hans Test");
        // Das Repository speichert und gibt den gleichen Kunden zurück
        when(customerRepository.save(any(Customer.class))).thenReturn(c);

        // Act: Kunde wird hinzugefügt
        Customer result = customerController.add(c);

        // Assert: Rückgabe stimmt mit dem gespeicherten Kunden überein
        assertEquals("Hans Test", result.getName());

        // Überprüfen, dass save() mit dem richtigen Kundenobjekt aufgerufen wurde
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository, times(1)).save(customerCaptor.capture());
        assertEquals("Hans Test", customerCaptor.getValue().getName());
    }
}