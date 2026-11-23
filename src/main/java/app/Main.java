package app;

import data_access.SqliteAnnotationDAO;
import database.DatabaseManager;
import interface_adapter.Annotation.AnnotationController;
import interface_adapter.Annotation.AnnotationPresenter;
import interface_adapter.Annotation.AnnotationViewModel;
import view.SwingView;
import use_case.add_annotation.AddAnnotationInteractor;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

/** Composition root: all concrete dependencies are wired here. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Path databaseDirectory = Paths.get(System.getProperty("user.home"), ".pdfnotes");
                Files.createDirectories(databaseDirectory);
                DatabaseManager database = new DatabaseManager(
                        databaseDirectory.resolve("app.db").toString());
                database.initSchema();
                ensureDefaultDocument(database);
                SqliteAnnotationDAO dataAccess = new SqliteAnnotationDAO(database);
                AnnotationViewModel viewModel = new AnnotationViewModel();
                AnnotationPresenter presenter = new AnnotationPresenter(viewModel);
                AnnotationController controller = new AnnotationController(
                        new AddAnnotationInteractor(dataAccess, presenter));
                new SwingView(controller, viewModel).setVisible(true);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Could not initialise SQLite: "
                        + exception.getMessage(), "Startup error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void ensureDefaultDocument(DatabaseManager database) {
        String sql = "INSERT OR IGNORE INTO documents "
                + "(id, title, pdf_path, page_count) VALUES (?,?,?,?)";
        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "unsaved-document");
            statement.setString(2, "Unsaved Document");
            statement.setString(3, "");
            statement.setInt(4, 1);
            statement.executeUpdate();
        } catch (Exception exception) {
            throw new RuntimeException("Could not initialise default document", exception);
        }
    }
}