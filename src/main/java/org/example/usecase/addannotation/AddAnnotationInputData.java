package org.example.usecase.addannotation;

public final class AddAnnotationInputData {
    private final String documentHash;
    private final int page;
    private final double x;
    private final double y;
    private final String text;

    public AddAnnotationInputData(String documentHash, int page, double x, double y, String text) {
        this.documentHash = documentHash;
        this.page = page;
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public String getDocumentHash() { return documentHash; }
    public int getPage() { return page; }
    public double getX() { return x; }
    public double getY() { return y; }
    public String getText() { return text; }
}
