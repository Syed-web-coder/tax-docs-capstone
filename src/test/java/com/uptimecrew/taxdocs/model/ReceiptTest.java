package com.uptimecrew.taxdocs.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReceiptTest {

    @Test
    void constructs_with_valid_inputs() {
        Receipt receipt = new Receipt("doc-001", new BigDecimal("1250.00"), "Office Supply Co", LocalDate.of(2026, 3, 1));

        assertEquals("doc-001", receipt.getDocumentId());
        assertEquals(0, new BigDecimal("1250.00").compareTo(receipt.getAmount()));
        assertEquals("Office Supply Co", receipt.getVendorName());
        assertEquals(LocalDate.of(2026, 3, 1), receipt.getIncurredOn());
    }

    @Test
    void rejects_null_vendorName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Receipt("doc-001", new BigDecimal("1250.00"), null, LocalDate.of(2026, 3, 1)));
    }

    @Test
    void rejects_negative_amount() {
        assertThrows(IllegalArgumentException.class, () ->
                new Receipt("doc-001", new BigDecimal("-1.00"), "Office Supply Co", LocalDate.of(2026, 3, 1)));
    }
}
