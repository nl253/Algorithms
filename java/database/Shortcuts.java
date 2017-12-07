package database;

import java.sql.*;
import java.util.Optional;

/**
 * @author nl253
 */

@SuppressWarnings({"NewClassNamingConvention", "CallToDriverManagerGetConnection", "CallToSystemExit", "CallToPrintStackTrace", "JDBCResourceOpenedButNotSafelyClosed", "JDBCExecuteWithNonConstantString", "StaticVariableUsedBeforeInitialization", "StaticVariableMayNotBeInitialized", "UtilityClassCanBeEnum", "FieldNamingConvention", "MethodWithMultipleReturnPoints", "SameParameterValue"})
public final class Shortcuts {

    private static final String PROTOCOL = "jdbc:sqlite:";
    private static final String DATABASE = String.format("%sdatabase.sqlite3", PROTOCOL);
    private static boolean connected;
    private static Statement statement;
    private static Connection connection;

    private Shortcuts() {}

    /**
     * @param queryString
     * @return
     *
     * @throws SQLException
     */

    @SuppressWarnings({"MethodWithMultipleReturnPoints", "WeakerAccess"})
    static Optional<ResultSet> query(final String queryString) throws SQLException {
        if (!connected) connect();
        if (getStatement().isPresent()) try {
            return Optional.ofNullable(statement.executeQuery(queryString));
        } catch (final SQLException ignored) {}
        return Optional.empty();
    }

    /**
     * @throws SQLException
     */

    @SuppressWarnings("CallToPrintStackTrace")
    private static void connect() throws SQLException {
        if (!connected) try (Connection connection = DriverManager
                .getConnection(DATABASE)) {
            Shortcuts.connection = connection;
            statement = connection.createStatement();
        }
    }

    public static boolean isConnected() {
        return connected;
    }

    /**
     * @throws SQLException
     */

    public static void close() throws SQLException {
        if (!connected) return;
        statement.close();
        connection.close();
        connected = false;
    }

    @SuppressWarnings({"StaticVariableUsedBeforeInitialization", "WeakerAccess"})
    static Optional<Statement> getStatement() {
        return Optional.ofNullable(statement);
    }

    @SuppressWarnings("StaticVariableUsedBeforeInitialization")
    public static Optional<Connection> getConnection() {
        return Optional.ofNullable(connection);
    }

    public static void main(final String... args) throws SQLException {
        query("CREATE if not exists DATABASE new_database")
                .ifPresent(System.out::println);
    }
}
