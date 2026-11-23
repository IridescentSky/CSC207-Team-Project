package interface_adapter.Annotation;

import use_case.add_annotation.AddAnnotationOutputBoundary;
import use_case.add_annotation.AddAnnotationOutputData;

public final class AnnotationPresenter implements AddAnnotationOutputBoundary {
    private final AnnotationViewModel viewModel;

    public AnnotationPresenter(AnnotationViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(AddAnnotationOutputData outputData) {
        viewModel.showSuccess("Annotation saved: " + outputData.getAnnotation().getId());
    }

    @Override
    public void prepareFailView(String error) {
        viewModel.showError(error == null ? "Unable to save annotation." : error);
    }
}