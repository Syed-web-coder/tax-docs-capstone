package com.uptimecrew.taxdocs.service;

import com.uptimecrew.taxdocs.model.Receipt;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class ReceiptRegistry {

    private final Map<String, Receipt> receiptsById;

    public ReceiptRegistry(Collection<Receipt> receipts) {
        Objects.requireNonNull(receipts, "receipts must not be null");
        LinkedHashMap<String, Receipt> map = new LinkedHashMap<>();
        for (Receipt receipt : receipts) {
            Objects.requireNonNull(receipt, "receipt must not be null");
            map.put(receipt.getDocumentId(), receipt);
        }
        this.receiptsById = Collections.unmodifiableMap(map);
    }

    public int size() {
        return receiptsById.size();
    }

    public Optional<Receipt> findById(String id) {
        Objects.requireNonNull(id, "id must not be null");
        return Optional.ofNullable(receiptsById.get(id));
    }

    public List<Receipt> findByVendorAbove(String vendorFragment, BigDecimal threshold) {
        Objects.requireNonNull(vendorFragment, "vendorFragment must not be null");
        Objects.requireNonNull(threshold, "threshold must not be null");
        return receiptsById.values().stream()
                .filter(r -> r.getVendorName().toLowerCase().contains(vendorFragment.toLowerCase()))
                .filter(r -> r.getAmount().compareTo(threshold) > 0)
                .sorted(Comparator.comparing(Receipt::getAmount).thenComparing(Receipt::getVendorName))
                .toList();
    }
}
