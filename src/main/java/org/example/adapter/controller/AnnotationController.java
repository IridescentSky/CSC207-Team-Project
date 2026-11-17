package org.example.adapter.controller;

import org.example.usecase.addannotation.AddAnnotationInput;
import org.example.usecase.addannotation.AddAnnotationInputData;

public final class AnnotationController {
    private final AddAnnotationInput inputBoundary;

    public AnnotationController(AddAnnotationInput inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void addAnnotation(String documentHash, int page, double x, double y, String text) {
        inputBoundary.addAnnotation(new AddAnnotationInputData(documentHash, page, x, y, text));
    }
}
