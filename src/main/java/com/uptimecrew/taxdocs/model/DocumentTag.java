package com.uptimecrew.taxdocs.model;

import java.util.Objects;

public final class DocumentTag {

    private final String name;
    private final String color;

    public DocumentTag(String name, String color) {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(color, "color must not be null");
        if (name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (color.isBlank()) {
            throw new IllegalArgumentException("color must not be blank");
        }
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentTag)) return false;
        DocumentTag that = (DocumentTag) o;
        return Objects.equals(name, that.name) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }

    @Override
    public String toString() {
        return "DocumentTag{name='" + name + "', color='" + color + "'}";
    }
}
