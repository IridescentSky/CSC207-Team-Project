package use_case.add_annotation;

import entity.Annotation;

public final class AddAnnotationInteractor implements AddAnnotationInputBoundary {
    private final AddAnnotationDataAccessInterface annotationDataAccess;
    private final AddAnnotationOutputBoundary output;

    public AddAnnotationInteractor(AddAnnotationDataAccessInterface annotationDataAccess,
                                   AddAnnotationOutputBoundary output) {
        this.annotationDataAccess = annotationDataAccess;
        this.output = output;
    }

    @Override
    public void addAnnotation(AddAnnotationInputData inputData) {
        try {
            Annotation annotation = new Annotation(
                    inputData.getDocumentId(), inputData.getPage(), inputData.getX(),
                    inputData.getY(), inputData.getWidth(), inputData.getHeight(),
                    inputData.getLatex(), inputData.getKind(), inputData.getRefersTo());
            annotationDataAccess.save(annotation);
            output.prepareSuccessView(new AddAnnotationOutputData(annotation));
        } catch (RuntimeException exception) {
            output.prepareFailView(exception.getMessage());
        }
    }
}
