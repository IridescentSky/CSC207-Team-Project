package use_case.add_annotation;

import entity.Annotation;

public final class AddAnnotationOutputData {
    private final Annotation annotation;

    public AddAnnotationOutputData(Annotation annotation) {
        this.annotation = annotation;
    }

    public Annotation getAnnotation() { return annotation; }
}
