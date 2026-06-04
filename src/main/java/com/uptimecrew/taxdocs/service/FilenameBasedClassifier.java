package com.uptimecrew.taxdocs.service;

import com.uptimecrew.taxdocs.model.DocumentCategory;

public final class FilenameBasedClassifier implements DocumentClassifier {

    @Override
    public DocumentCategory classify(String filename) {
        if (filename == null || filename.isBlank()) {
            throw new IllegalArgumentException("filename must not be null or blank");
        }
        String lower = filename.toLowerCase();
        if (lower.contains("receipt")) {
            return DocumentCategory.RECEIPT;
        }
        if (lower.contains("invoice")) {
            return DocumentCategory.INVOICE;
        }
        if (lower.contains("w2") || lower.contains("w-2")) {
            return DocumentCategory.W2;
        }
        if (lower.contains("bank") || lower.contains("statement")) {
            return DocumentCategory.BANK_STATEMENT;
        }
        return DocumentCategory.OTHER;
    }
}
