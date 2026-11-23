package use_case.add_annotation;

public final class AddAnnotationInputData {
    private final String documentId;
    private final int page;
    private final Double x;
    private final Double y;
    private final Double width;
    private final Double height;
    private final String latex;
    private final String kind;
    private final String refersTo;

    public AddAnnotationInputData(String documentId, int page, Double x, Double y,
                                  Double width, Double height, String latex,
                                  String kind, String refersTo) {
        this.documentId = documentId;
        this.page = page;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.latex = latex;
        this.kind = kind;
        this.refersTo = refersTo;
    }

    public String getDocumentId() { return documentId; }
    public int getPage() { return page; }
    public Double getX() { return x; }
    public Double getY() { return y; }
    public Double getWidth() { return width; }
    public Double getHeight() { return height; }
    public String getLatex() { return latex; }
    public String getKind() { return kind; }
    public String getRefersTo() { return refersTo; }
}
