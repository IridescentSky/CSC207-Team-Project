package use_case.add_annotation;

public interface AddAnnotationOutputBoundary {
    void prepareSuccessView(AddAnnotationOutputData outputData);
    void prepareFailView(String error);
}
