package org.example.entity;

import java.util.Objects;

/** Metadata for an imported PDF. The file itself remains on disk. */
public final class PdfDocument {
    private final String hash;
    private final String title;
    private final String filePath;

    public PdfDocument(String hash, String title, String filePath) {
        this.hash = requireNonBlank(hash, "hash");
        this.title = requireNonBlank(title, "title");
        this.filePath = requireNonBlank(filePath, "filePath");
    }

    public String getHash() { return hash; }
    public String getTitle() { return title; }
    public String getFilePath() { return filePath; }

    private static String requireNonBlank(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PdfDocument)) return false;
        PdfDocument that = (PdfDocument) other;
        return hash.equals(that.hash);
    }

    @Override
    public int hashCode() { return Objects.hash(hash); }
}
