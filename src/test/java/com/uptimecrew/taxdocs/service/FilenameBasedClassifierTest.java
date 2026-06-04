package com.uptimecrew.taxdocs.service;

import com.uptimecrew.taxdocs.model.DocumentCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilenameBasedClassifierTest {

    @Test
    void classifies_receipt_filename() {
        DocumentClassifier classifier = new FilenameBasedClassifier();
        DocumentCategory result = classifier.classify("dinner-receipt.pdf");

        assertNotNull(result);
        assertEquals(DocumentCategory.RECEIPT, result);
        assertTrue(classifier instanceof FilenameBasedClassifier);
    }
}
