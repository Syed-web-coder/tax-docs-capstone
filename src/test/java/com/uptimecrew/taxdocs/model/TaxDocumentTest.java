package com.uptimecrew.taxdocs.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaxDocumentTest {

    @Test
    void constructs_with_valid_inputs() {
        Instant uploadedAt = Instant.parse("2026-03-01T10:00:00Z");
        TaxDocument doc = new TaxDocument("doc-001", "user-syed", DocumentCategory.RECEIPT, uploadedAt, "receipt.pdf", 50000L);

        assertEquals("doc-001", doc.getId());
        assertEquals("user-syed", doc.getOwnerId());
        assertEquals(DocumentCategory.RECEIPT, doc.getCategory());
        assertEquals(uploadedAt, doc.getUploadedAt());
        assertEquals("receipt.pdf", doc.getOriginalFilename());
        assertEquals(50000L, doc.getSizeBytes());
    }

    @Test
    void rejects_null_id() {
        assertThrows(NullPointerException.class, () ->
                new TaxDocument(null, "user-syed", DocumentCategory.RECEIPT, Instant.parse("2026-03-01T10:00:00Z"), "receipt.pdf", 50000L));
    }

    @Test
    void rejects_negative_size() {
        assertThrows(IllegalArgumentException.class, () ->
                new TaxDocument("doc-001", "user-syed", DocumentCategory.RECEIPT, Instant.parse("2026-03-01T10:00:00Z"), "receipt.pdf", -1L));
    }
}
