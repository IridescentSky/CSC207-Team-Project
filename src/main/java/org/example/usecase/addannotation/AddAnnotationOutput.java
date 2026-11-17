package org.example.usecase.addannotation;

public interface AddAnnotationOutput {
    void prepareSuccessView(AddAnnotationOutputData outputData);
    void prepareFailView(String error);
}
