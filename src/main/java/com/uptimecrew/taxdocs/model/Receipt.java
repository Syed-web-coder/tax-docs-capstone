package com.uptimecrew.taxdocs.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public final class Receipt {

    private final String documentId;
    private final BigDecimal amount;
    private final String vendorName;
    private final LocalDate incurredOn;

    public Receipt(String documentId, BigDecimal amount, String vendorName, LocalDate incurredOn) {
        if (documentId == null || documentId.isBlank()) {
            throw new IllegalArgumentException("documentId must not be null or blank");
        }
        if (amount == null) {
            throw new IllegalArgumentException("amount must not be null");
        }
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }
        if (vendorName == null || vendorName.isBlank()) {
            throw new IllegalArgumentException("vendorName must not be null or blank");
        }
        if (incurredOn == null) {
            throw new IllegalArgumentException("incurredOn must not be null");
        }
        this.documentId = documentId;
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.vendorName = vendorName;
        this.incurredOn = incurredOn;
    }

    public String getDocumentId() {
        return documentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getVendorName() {
        return vendorName;
    }

    public LocalDate getIncurredOn() {
        return incurredOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receipt)) return false;
        Receipt other = (Receipt) o;
        return Objects.equals(documentId, other.documentId)
                && Objects.equals(amount, other.amount)
                && Objects.equals(vendorName, other.vendorName)
                && Objects.equals(incurredOn, other.incurredOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, amount, vendorName, incurredOn);
    }

    @Override
    public String toString() {
        return "Receipt{"
                + "documentId='" + documentId + '\''
                + ", amount=" + amount
                + ", vendorName='" + vendorName + '\''
                + ", incurredOn=" + incurredOn
                + '}';
    }
}
