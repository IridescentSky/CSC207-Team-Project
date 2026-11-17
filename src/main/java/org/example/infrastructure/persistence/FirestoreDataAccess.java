package org.example.infrastructure.persistence;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.example.entity.Annotation;
import org.example.usecase.AnnotationDataAccess;

import java.util.ArrayList;
import java.util.List;

///** Firestore adapter. Firebase credentials are supplied through application-default credentials. */
public final class FirestoreDataAccess implements AnnotationDataAccess {
    private static final String COLLECTION = "annotations";
    private final Firestore firestore;

    public FirestoreDataAccess(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void save(Annotation annotation) {
        try {
            ApiFuture<WriteResult> write = firestore.collection(COLLECTION)
                    .document(annotation.getId())
                    .set(toDocument(annotation));
            write.get();
        } catch (Exception exception) {
            throw new IllegalStateException("Could not save annotation to Firestore", exception);
        }
    }

    @Override
    public List<Annotation> findByDocument(String documentHash) {
        try {
            ApiFuture<QuerySnapshot> query = firestore.collection(COLLECTION)
                    .whereEqualTo("documentHash", documentHash)
                    .get();
            List<Annotation> annotations = new ArrayList<>();
            for (DocumentSnapshot document : query.get().getDocuments()) {
                annotations.add(new Annotation(document.getId(),
                        document.getString("documentHash"),
                        document.getLong("page").intValue(),
                        document.getDouble("x"),
                        document.getDouble("y"),
                        document.getString("text")));
            }
            return annotations;
        } catch (Exception exception) {
            throw new IllegalStateException("Could not load annotations from Firestore", exception);
        }
    }

    private static Object toDocument(Annotation annotation) {
        return java.util.Map.of(
                "documentHash", annotation.getDocumentHash(),
                "page", annotation.getPage(),
                "x", annotation.getX(),
                "y", annotation.getY(),
                "text", annotation.getText());
    }
}
