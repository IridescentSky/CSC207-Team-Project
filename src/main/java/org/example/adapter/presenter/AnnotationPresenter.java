package org.example.adapter.presenter;

import org.example.usecase.addannotation.AddAnnotationOutput;
import org.example.usecase.addannotation.AddAnnotationOutputData;

public final class AnnotationPresenter implements AddAnnotationOutput {
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
