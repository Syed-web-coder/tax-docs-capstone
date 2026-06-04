package com.uptimecrew.taxdocs.service;

import com.uptimecrew.taxdocs.model.DocumentCategory;

public interface DocumentClassifier {
    DocumentCategory classify(String filename);
}
