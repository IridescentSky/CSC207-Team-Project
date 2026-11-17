package org.example.usecase;

import org.example.entity.Annotation;
import java.util.List;

/** Application-facing persistence boundary. */
public interface AnnotationDataAccess {
    void save(Annotation annotation);
    List<Annotation> findByDocument(String documentHash);
}
