package entity;

import java.util.Objects;

/** Metadata for an imported PDF. The file itself remains on disk. */
public final class PdfDocument {
    private final String id;
    private final String title;
    private final String filePath;
    private final int pageCount;

    public PdfDocument(String id, String title, String filePath, int pageCount) {
        this.id = requireNonBlank(id, "id");
        this.title = requireNonBlank(title, "title");
        this.filePath = requireNonBlank(filePath, "filePath");
        if (pageCount < 1) throw new IllegalArgumentException("pageCount must be positive");
        this.pageCount = pageCount;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getFilePath() { return filePath; }
    public int getPageCount() { return pageCount; }

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
        return id.equals(that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}