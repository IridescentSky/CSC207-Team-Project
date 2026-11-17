package org.example.usecase.addannotation;

import org.example.entity.Annotation;

public final class AddAnnotationOutputData {
    private final Annotation annotation;

    public AddAnnotationOutputData(Annotation annotation) {
        this.annotation = annotation;
    }

    public Annotation getAnnotation() { return annotation; }
}
