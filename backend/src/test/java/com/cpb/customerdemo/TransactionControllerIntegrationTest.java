package com.cpb.customerdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void createAndReadTransaction() throws Exception {
        // Arrange: Erstelle einen Kunden
        Customer customer = customerRepo.save(new Customer("Testkunde"));
        TransactionRequest req = new TransactionRequest(
                customer.getId(), new BigDecimal("123.45"), "Testüberweisung"
        );

        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(123.45))
                .andExpect(jsonPath("$.description").value("Testüberweisung"));

        // Assert: Lese alle Transaktionen für Kunde
        mockMvc.perform(get("/api/transactions/customer/" + customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Testüberweisung"));
    }
}