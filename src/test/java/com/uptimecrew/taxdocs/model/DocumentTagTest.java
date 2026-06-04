package com.uptimecrew.taxdocs.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DocumentTagTest {

    @Test
    void constructs_with_valid_inputs() {
        DocumentTag tag = new DocumentTag("urgent", "#FF0000");

        assertEquals("urgent", tag.getName());
        assertEquals("#FF0000", tag.getColor());
    }

    @Test
    void rejects_null_name() {
        assertThrows(NullPointerException.class, () -> new DocumentTag(null, "#FF0000"));
    }

    @Test
    void rejects_blank_color() {
        assertThrows(IllegalArgumentException.class, () -> new DocumentTag("urgent", "   "));
    }
}
