package com.uptimecrew.taxdocs.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReceiptDraftTest {

    @Test
    void constructs_with_valid_inputs() {
        ReceiptDraft subject = new ReceiptDraft(
            "rcpt-synth-001",
            new BigDecimal("1250.00"),
            "Office Supply Co",
            LocalDate.of(2026, 3, 1)
        );
        assertEquals("rcpt-synth-001", subject.getId());
        assertEquals(0, new BigDecimal("1250.00").compareTo(subject.getAmount()));
        assertEquals("Office Supply Co", subject.getVendorName());
        assertEquals(LocalDate.of(2026, 3, 1), subject.getIncurredOn());
    }

    @Test
    void rejects_null_vendorName() {
        assertThrows(NullPointerException.class, () -> new ReceiptDraft(
            "rcpt-synth-001",
            new BigDecimal("1250.00"),
            null,
            LocalDate.of(2026, 3, 1)
        ));
    }

    @Test
    void rejects_negative_amount() {
        assertThrows(IllegalArgumentException.class, () -> new ReceiptDraft(
            "rcpt-synth-001",
            new BigDecimal("-1.00"),
            "Office Supply Co",
            LocalDate.of(2026, 3, 1)
        ));
    }

    @Test
    void equal_instances_have_equal_hashcodes() {
        ReceiptDraft a = new ReceiptDraft(
            "rcpt-synth-001",
            new BigDecimal("1250.00"),
            "Office Supply Co",
            LocalDate.of(2026, 3, 1)
        );
        ReceiptDraft b = new ReceiptDraft(
            "rcpt-synth-001",
            new BigDecimal("1250.00"),
            "Office Supply Co",
            LocalDate.of(2026, 3, 1)
        );
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}
