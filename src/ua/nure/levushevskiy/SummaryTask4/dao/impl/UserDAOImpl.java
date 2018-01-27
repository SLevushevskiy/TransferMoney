package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import ua.nure.levushevskiy.SummaryTask4.dao.api.UserDAO;
import ua.nure.levushevskiy.SummaryTask4.dao.exeption.DAOException;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Implementation of the UserDAO interface.
 */
public class UserDAOImpl implements UserDAO {

    /**
     * Request to retrieve all user objects by ID.
     */
    public static final String SQL_SELECT_BY_ID = "SELECT * FROM st4db.user WHERE idUser = ?";
    /**
     * Request to retrieve all user objects.
     */
    public static final String SQL_SELECT_ALL = "SELECT * FROM st4db.user";
    /**
     * Request for insert user.
     */
    public static final String SQL_USER_INSERT = "INSERT INTO st4db.user (name, surname, email, password, Role_id,"
            + "UserStatus_id) VALUES(?, ?, ?, ?, ?, ?)";
    /**
     * Request to retrieve all user objects by email.
     */
    public static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM st4db.user WHERE email = ?";
    /**
     * Request to retrieve all user objects by email and password.
     */
    public static final String SQL_SELECT_BY_EMAIL_AND_PASSWORD = "SELECT * FROM st4db.user WHERE email = ? AND password = ?";
    /**
     * Request to update the user status in the database.
     */
    public static final String SQL_UPDATE_USER_STATUS = "UPDATE st4db.user SET UserStatus_id = "
            + "(SELECT idUser_status FROM st4db.user_status WHERE status = ?) WHERE idUser = ?";


    /**
     * Object of connection pool.
     */
    private final ConnectionPool dataSource;

    /**
     * The Logger object for logging events of DAO class.
     */
    private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);


    /**
     * A constructor that takes a pool of connections.
     *
     * @param dataSource - connection pool.
     */
    public UserDAOImpl(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public final User save(final User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setNString(1, user.getName());
            preparedStatement.setNString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, (int) user.getRoleId());
            preparedStatement.setInt(6, (int) user.getUserStatusId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setIdUser(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while saving user!", e);
        } finally {
            closeConnection(connection);
        }
        return user;
    }

    public final boolean hasUserWithEmail(final String email) {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            if (!extractResultSet(rs).isEmpty()) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return false;
    }

    @Override
    public final User getByEmailAndPassword(final String email, final String password) {
        List<User> userList = null;
        User user = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();

            userList = extractResultSet(rs);
            if (!userList.isEmpty()) {
                user = userList.get(0);
            }

        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return user;
    }

    @Override
    public final boolean updateUserStatus(final int id, String userStatus) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_STATUS)) {
            preparedStatement.setString(1, userStatus);
            preparedStatement.setInt(2, id);
            int changes = preparedStatement.executeUpdate();
            if (changes > 0) {
                return true;
            }
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public final User getById(final Integer id) {
        List<User> userList = null;
        User user = null;

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            userList = extractResultSet(rs);

            if (userList.size() != 1) {
                throw new DAOException("Cannot get commodity by id");
            }
            user = userList.get(0);
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
            closeResultSet(rs);
        }
        return user;
    }

    @Override
    public final boolean delete(Integer userId) {
        return false;
    }

    @Override
    public final boolean update(final User t) {
        return false;
    }

    @Override
    public final List<User> getAll() {
        List<User> userList = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Cannot get connection!", e);
        }

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_SELECT_ALL)) {
            userList = extractResultSet(rs);

        } catch (SQLException e) {
            LOG.error(e);
            throw new DAOException("Error while extraction result set!", e);
        } finally {
            closeConnection(connection);
        }
        return userList;
    }

    /**
     * Method for extracting objects from the Reset set.
     *
     * @param rs - result set.
     * @return - list of objects.
     * @throws SQLException - can be caused by one of the methods of the Result Set.
     */
    private List<User> extractResultSet(final ResultSet rs) throws SQLException {
        List<User> userList = new ArrayList<>();
        User user;
        while (rs.next()) {
            user = new User();
            user.setIdUser(rs.getInt("idUser"));
            user.setName(rs.getString("Name"));
            user.setSurname(rs.getString("Surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRoleId(rs.getInt("Role_id"));
            user.setName(rs.getString("name"));
            user.setUserStatusId(rs.getInt("UserStatus_id"));
            userList.add(user);
        }
        return userList;
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
