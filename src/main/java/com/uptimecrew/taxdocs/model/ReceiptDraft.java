package com.uptimecrew.taxdocs.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public final class ReceiptDraft {

    private final String id;
    private final BigDecimal amount;
    private final String vendorName;
    private final LocalDate incurredOn;

    public ReceiptDraft(String id, BigDecimal amount, String vendorName, LocalDate incurredOn) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(amount, "amount");
        Objects.requireNonNull(vendorName, "vendorName");
        Objects.requireNonNull(incurredOn, "incurredOn");
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }
        this.id = id;
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.vendorName = vendorName;
        this.incurredOn = incurredOn;
    }

    public String getId() { return id; }
    public BigDecimal getAmount() { return amount; }
    public String getVendorName() { return vendorName; }
    public LocalDate getIncurredOn() { return incurredOn; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReceiptDraft other)) return false;
        return Objects.equals(id, other.id)
            && Objects.equals(amount, other.amount)
            && Objects.equals(vendorName, other.vendorName)
            && Objects.equals(incurredOn, other.incurredOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, vendorName, incurredOn);
    }

    @Override
    public String toString() {
        return "ReceiptDraft{id=" + id + ", amount=" + amount
            + ", vendorName=" + vendorName + ", incurredOn=" + incurredOn + "}";
    }
}
