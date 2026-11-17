package org.example.usecase.addannotation;

import org.example.entity.Annotation;
import org.example.usecase.AnnotationDataAccess;

public final class AddAnnotationInteractor implements AddAnnotationInput {
    private final AnnotationDataAccess annotationDataAccess;
    private final AddAnnotationOutput output;

    public AddAnnotationInteractor(AnnotationDataAccess annotationDataAccess, AddAnnotationOutput output) {
        this.annotationDataAccess = annotationDataAccess;
        this.output = output;
    }

    @Override
    public void addAnnotation(AddAnnotationInputData inputData) {
        try {
            Annotation annotation = new Annotation(
                    inputData.getDocumentHash(), inputData.getPage(), inputData.getX(),
                    inputData.getY(), inputData.getText());
            annotationDataAccess.save(annotation);
            output.prepareSuccessView(new AddAnnotationOutputData(annotation));
        } catch (RuntimeException exception) {
            output.prepareFailView(exception.getMessage());
        }
    }
}
