package org.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.example.adapter.controller.AnnotationController;
import org.example.adapter.presenter.AnnotationPresenter;
import org.example.adapter.presenter.AnnotationViewModel;
import org.example.infrastructure.persistence.FirestoreDataAccess;
import org.example.infrastructure.ui.SwingView;
import org.example.usecase.addannotation.AddAnnotationInteractor;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.io.IOException;

/** Composition root: all concrete dependencies are wired here. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                if (FirebaseApp.getApps().isEmpty()) {
                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.getApplicationDefault())
                            .build();
                    FirebaseApp.initializeApp(options);
                }
                FirestoreDataAccess dataAccess = new FirestoreDataAccess(FirestoreClient.getFirestore());
                AnnotationViewModel viewModel = new AnnotationViewModel();
                AnnotationPresenter presenter = new AnnotationPresenter(viewModel);
                AnnotationController controller = new AnnotationController(
                        new AddAnnotationInteractor(dataAccess, presenter));
                new SwingView(controller, viewModel).setVisible(true);
            } catch (IOException | RuntimeException exception) {
                JOptionPane.showMessageDialog(null, "Could not connect to Firebase: "
                        + exception.getMessage(), "Startup error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
