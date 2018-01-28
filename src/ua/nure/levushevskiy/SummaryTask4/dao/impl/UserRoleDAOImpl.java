package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.apache.log4j.Logger;
import ua.nure.levushevskiy.SummaryTask4.dao.api.UserRoleDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.exeption.DAOException;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAOImpl implements UserRoleDAO {

    /**
     * Request to retrieve all role objects by ID.
     */
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM st4db.user_role WHERE idUserRole = ?";

    /**
     * Object of connection pool.
     */
    private final ConnectionPool dataSource;

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(UserRoleDAOImpl.class);


    /**
     * A constructor that takes a pool of connections.
     *
     * @param dataSource - connection pool.
     */
    public UserRoleDAOImpl(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Gets the role object by user ID.
     *
     * @param userId - user ID.
     * @return - userRole object
     */
    @Override
    public UserRole getUserRole(int userId) {
        UserRole role = null;

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, userId);
            rs = preparedStatement.executeQuery();
            List<UserRole> roleList = extractResultSet(rs);

            if (roleList.size() != 1) {
                throw new DAOException("Cannot get commodity by id");
            }
            role = roleList.get(0);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return role;
    }


    /**
     * Method for extracting objects from the Reset set.
     *
     * @param rs - result set.
     * @return - list of objects.
     * @throws SQLException - can be caused by one of the methods of the Result Set.
     */
    private List<UserRole> extractResultSet(final ResultSet rs) throws SQLException {
        List<UserRole> roleList = new ArrayList<>();
        UserRole role;
        while (rs.next()) {
            role = new UserRole();
            role.setIdUserRole(rs.getInt("idUserRole"));
            role.setRank(rs.getString("rank"));
            roleList.add(role);
        }
        return roleList;
    }

    /**
     * Method for closing the Result Set.
     *
     * @param rs - Result Set object.
     */
    private void closeResultSet(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(ex);
                throw new DAOException("Cannot close result set", ex);
            }
        }
    }

    /**
     * Method for closing the connection to the database.
     *
     * @param connection - connection object.
     */
    private void closeConnection(final Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e);
                throw new DAOException("Cannot close connection", e);
            }
        }
    }

}
