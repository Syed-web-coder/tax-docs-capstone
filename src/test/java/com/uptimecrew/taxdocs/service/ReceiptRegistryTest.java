package com.uptimecrew.taxdocs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.uptimecrew.taxdocs.model.DocumentCategory;
import com.uptimecrew.taxdocs.model.Receipt;

class ReceiptRegistryTest {

    private ReceiptRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new ReceiptRegistry(List.of(
            new Receipt("doc-001", new BigDecimal("500.00"), "Office Supply Co", LocalDate.of(2026, 1, 1)),
            new Receipt("doc-002", new BigDecimal("1250.00"), "Tech Supply Store", LocalDate.of(2026, 1, 2)),
            new Receipt("doc-003", new BigDecimal("75.00"), "Corner Bakery", LocalDate.of(2026, 1, 3))
        ));
    }

    @Test
    void size_returns_correct_count() {
        assertEquals(3, registry.size());
    }

    @Test
    void findById_returns_receipt_when_found() {
        Optional<Receipt> result = registry.findById("doc-001");
        assertTrue(result.isPresent());
        assertEquals("doc-001", result.orElseThrow().getDocumentId());
    }

    @Test
    void findById_returns_empty_when_not_found() {
        Optional<Receipt> result = registry.findById("doc-999");
        assertTrue(result.isEmpty());
    }

    @Test
    void findByVendorAbove_returns_matching_receipts() {
        List<Receipt> result = registry.findByVendorAbove("Supply", new BigDecimal("100.00"));
        assertEquals(2, result.size());
        assertTrue(result.get(0).getVendorName().contains("Office"));
    }

    @Test
    void rejects_null_id() {
        assertThrows(NullPointerException.class, () -> registry.findById(null));
    }
}
