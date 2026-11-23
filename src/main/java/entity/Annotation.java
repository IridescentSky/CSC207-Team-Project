package entity;

import java.util.Objects;
import java.util.UUID;

/** A user-authored or generated note positioned on a PDF page. */
public final class Annotation {
    private final String id;
    private final String documentId;
    private final int page;
    private final Double x;
    private final Double y;
    private final Double width;
    private final Double height;
    private final String latex;
    private final String kind;
    private final String refersTo;

    public Annotation(String documentId, int page, Double x, Double y,
                      Double width, Double height, String latex, String kind, String refersTo) {
        this(UUID.randomUUID().toString(), documentId, page, x, y, width, height,
                latex, kind, refersTo);
    }

    public Annotation(String id, String documentId, int page, Double x, Double y,
                      Double width, Double height, String latex, String kind, String refersTo) {
        this.id = requireNonBlank(id, "id");
        this.documentId = requireNonBlank(documentId, "documentId");
        if (page < 1) throw new IllegalArgumentException("page must be positive");
        requireFiniteIfPresent(x, "x");
        requireFiniteIfPresent(y, "y");
        requireFiniteIfPresent(width, "width");
        requireFiniteIfPresent(height, "height");
        this.page = page;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.latex = requireNonBlank(latex, "latex");
        this.kind = requireNonBlank(kind, "kind");
        if (!"manual".equals(kind) && !"auto-reference".equals(kind)) {
            throw new IllegalArgumentException("kind must be manual or auto-reference");
        }
        this.refersTo = refersTo;
    }

    public String getId() { return id; }
    public String getDocumentId() { return documentId; }
    public int getPage() { return page; }
    public Double getX() { return x; }
    public Double getY() { return y; }
    public Double getWidth() { return width; }
    public Double getHeight() { return height; }
    public String getLatex() { return latex; }
    public String getKind() { return kind; }
    public String getRefersTo() { return refersTo; }

    private static String requireNonBlank(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    private static void requireFiniteIfPresent(Double value, String field) {
        if (value != null && !Double.isFinite(value)) {
            throw new IllegalArgumentException(field + " must be finite");
        }
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Annotation && id.equals(((Annotation) other).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}