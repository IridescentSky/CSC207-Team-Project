package org.example.entity;

import java.util.Objects;
import java.util.UUID;

/** A user-authored or generated note positioned on a PDF page. */
public final class Annotation {
    private final String id;
    private final String documentHash;
    private final int page;
    private final double x;
    private final double y;
    private final String text;

    public Annotation(String documentHash, int page, double x, double y, String text) {
        this(UUID.randomUUID().toString(), documentHash, page, x, y, text);
    }

    public Annotation(String id, String documentHash, int page, double x, double y, String text) {
        this.id = requireNonBlank(id, "id");
        this.documentHash = requireNonBlank(documentHash, "documentHash");
        if (page < 1) throw new IllegalArgumentException("page must be positive");
        if (!Double.isFinite(x) || !Double.isFinite(y)) {
            throw new IllegalArgumentException("coordinates must be finite");
        }
        this.page = page;
        this.x = x;
        this.y = y;
        this.text = requireNonBlank(text, "text");
    }

    public String getId() { return id; }
    public String getDocumentHash() { return documentHash; }
    public int getPage() { return page; }
    public double getX() { return x; }
    public double getY() { return y; }
    public String getText() { return text; }

    private static String requireNonBlank(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Annotation && id.equals(((Annotation) other).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
