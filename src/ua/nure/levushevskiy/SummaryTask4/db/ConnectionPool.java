package ua.nure.levushevskiy.SummaryTask4.db;

import org.apache.commons.dbcp.BasicDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Connection pool.
 */
public class ConnectionPool {
    /**
     * An object that contains connections.
     */
    private BasicDataSource ds;

    /**
     * Minimum idle parameter.
     */
    private static final int MIN_IDLE = 5;

    /**
     * Maximum idle parameter.
     */
    private static final int MAX_IDLE = 20;

    /**
     * The maximum number of open prepared statements.
     */
    private static final int MAX_OPEN_PREPARED_STATEMENTS = 180;

    /**
     * A constructor that initializes the connection pool.
     *
     * @param bundle - database properties.
     * @throws IOException           - can be thrown while creating a class object of BasicDataSource.
     * @throws SQLException          - can be thrown while creating a class object of BasicDataSource.
     * @throws PropertyVetoException - can be thrown while creating a class object of BasicDataSource.
     */
    public ConnectionPool(final ResourceBundle bundle) throws IOException, SQLException, PropertyVetoException {
        String url = bundle.getString("db.url");
        String user = bundle.getString("db.user");
        String password = bundle.getString("db.password");
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setUrl(url);
        ds.setMinIdle(MIN_IDLE);
        ds.setMaxIdle(MAX_IDLE);
        ds.setMaxOpenPreparedStatements(MAX_OPEN_PREPARED_STATEMENTS);
    }

    /**
     * Method for obtaining a connection.
     *
     * @return - connection.
     * @throws SQLException - can be invoked by method of BasicDataSource class.
     */
    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
}
