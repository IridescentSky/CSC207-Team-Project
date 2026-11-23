package interface_adapter.Annotation;

import use_case.add_annotation.AddAnnotationInputBoundary;
import use_case.add_annotation.AddAnnotationInputData;

public final class AnnotationController {
    private final AddAnnotationInputBoundary inputBoundary;

    public AnnotationController(AddAnnotationInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void addAnnotation(String documentId, int page, Double x, Double y,
                              Double width, Double height, String latex,
                              String kind, String refersTo) {
        inputBoundary.addAnnotation(new AddAnnotationInputData(
                documentId, page, x, y, width, height, latex, kind, refersTo));
    }
}