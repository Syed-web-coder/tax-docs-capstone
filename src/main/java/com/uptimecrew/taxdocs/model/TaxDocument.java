package com.uptimecrew.taxdocs.model;

import java.time.Instant;
import java.util.Objects;

public final class TaxDocument {

    private final String id;
    private final String ownerId;
    private final DocumentCategory category;
    private final Instant uploadedAt;
    private final String originalFilename;
    private final long sizeBytes;

    public TaxDocument(String id, String ownerId, DocumentCategory category, Instant uploadedAt, String originalFilename, long sizeBytes) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.ownerId = Objects.requireNonNull(ownerId, "ownerId must not be null");
        this.category = Objects.requireNonNull(category, "category must not be null");
        this.uploadedAt = Objects.requireNonNull(uploadedAt, "uploadedAt must not be null");
        Objects.requireNonNull(originalFilename, "originalFilename must not be null");
        if (originalFilename.isBlank()) {
            throw new IllegalArgumentException("originalFilename must not be blank");
        }
        this.originalFilename = originalFilename;
        if (sizeBytes < 0) {
            throw new IllegalArgumentException("sizeBytes must be >= 0");
        }
        this.sizeBytes = sizeBytes;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public DocumentCategory getCategory() {
        return category;
    }

    public Instant getUploadedAt() {
        return uploadedAt;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaxDocument other)) return false;
        return sizeBytes == other.sizeBytes
                && Objects.equals(id, other.id)
                && Objects.equals(ownerId, other.ownerId)
                && Objects.equals(category, other.category)
                && Objects.equals(uploadedAt, other.uploadedAt)
                && Objects.equals(originalFilename, other.originalFilename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, category, uploadedAt, originalFilename, sizeBytes);
    }

    @Override
    public String toString() {
        return "TaxDocument{id='" + id + "', ownerId='" + ownerId + "', category=" + category
                + ", uploadedAt=" + uploadedAt + ", originalFilename='" + originalFilename
                + "', sizeBytes=" + sizeBytes + "}";
    }
}
