package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseManager {
    private final String url;

    public DatabaseManager(String filePath) {
        this.url = "jdbc:sqlite:" + filePath;
    }

    public Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        try (Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
        }
        return connection;
    }

    public void initSchema() {
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS documents ("
                    + "id TEXT PRIMARY KEY,"
                    + "title TEXT NOT NULL,"
                    + "pdf_path TEXT NOT NULL,"
                    + "page_count INTEGER NOT NULL"
                    + ")");
            statement.execute("CREATE TABLE IF NOT EXISTS annotations ("
                    + "id TEXT PRIMARY KEY,"
                    + "document_id TEXT NOT NULL REFERENCES documents(id) ON DELETE CASCADE,"
                    + "page INTEGER NOT NULL,"
                    + "x REAL, y REAL, width REAL, height REAL,"
                    + "latex TEXT NOT NULL,"
                    + "kind TEXT NOT NULL,"
                    + "refers_to TEXT REFERENCES annotations(id)"
                    + ")");
            statement.execute("CREATE INDEX IF NOT EXISTS idx_ann_doc_page "
                    + "ON annotations(document_id, page)");
            statement.execute("CREATE INDEX IF NOT EXISTS idx_ann_refers "
                    + "ON annotations(refers_to)");
        } catch (SQLException exception) {
            throw new RuntimeException("Could not initialise database", exception);
        }
    }
}
