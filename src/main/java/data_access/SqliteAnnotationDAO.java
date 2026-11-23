package data_access;

import database.DatabaseManager;
import entity.Annotation;
import use_case.add_annotation.AddAnnotationDataAccessInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SqliteAnnotationDAO implements AddAnnotationDataAccessInterface {
    private final DatabaseManager db;

    public SqliteAnnotationDAO(DatabaseManager db) {
        this.db = db;
    }

    @Override
    public void save(Annotation annotation) {
        String sql = "INSERT INTO annotations "
                + "(id, document_id, page, x, y, width, height, latex, kind, refers_to) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, annotation.getId());
            statement.setString(2, annotation.getDocumentId());
            statement.setInt(3, annotation.getPage());
            setNullableDouble(statement, 4, annotation.getX());
            setNullableDouble(statement, 5, annotation.getY());
            setNullableDouble(statement, 6, annotation.getWidth());
            setNullableDouble(statement, 7, annotation.getHeight());
            statement.setString(8, annotation.getLatex());
            statement.setString(9, annotation.getKind());
            statement.setString(10, annotation.getRefersTo());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to save annotation", exception);
        }
    }

    public List<Annotation> findByDocumentAndPage(String documentId, int page) {
        String sql = "SELECT * FROM annotations WHERE document_id = ? AND page = ?";
        List<Annotation> annotations = new ArrayList<>();
        try (Connection connection = db.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, documentId);
            statement.setInt(2, page);
            try (ResultSet rows = statement.executeQuery()) {
                while (rows.next()) {
                    annotations.add(new Annotation(
                            rows.getString("id"),
                            rows.getString("document_id"),
                            rows.getInt("page"),
                            getNullableDouble(rows, "x"),
                            getNullableDouble(rows, "y"),
                            getNullableDouble(rows, "width"),
                            getNullableDouble(rows, "height"),
                            rows.getString("latex"),
                            rows.getString("kind"),
                            rows.getString("refers_to")));
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to load annotations", exception);
        }
        return annotations;
    }

    private static void setNullableDouble(PreparedStatement statement, int index, Double value)
            throws SQLException {
        if (value == null) {
            statement.setNull(index, java.sql.Types.REAL);
        } else {
            statement.setDouble(index, value);
        }
    }

    private static Double getNullableDouble(ResultSet rows, String column) throws SQLException {
        double value = rows.getDouble(column);
        return rows.wasNull() ? null : value;
    }
}
