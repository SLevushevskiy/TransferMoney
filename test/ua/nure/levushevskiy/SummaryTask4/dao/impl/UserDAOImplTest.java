package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ua.nure.levushevskiy.SummaryTask4.dao.api.UserDAO;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.User;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOImplTest {

    private static UserDAO userDAO = null;
    private static User testUser = null;

    @BeforeClass
    public static void beforeClass() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("dataBase");
        ConnectionPool dataSource = null;
        try {
            dataSource = new ConnectionPool(resourceBundle);
        } catch (IOException | SQLException | PropertyVetoException e) {
            throw new IllegalArgumentException("Incorrect database properties!");
        }

        userDAO = new UserDAOImpl(dataSource);
        testUser = new User("Name","Surname","email@mail.ru","password",1,1);

    }

    @Test
    public void test01Save() throws Exception {
        Assert.assertNotNull(userDAO.save(testUser));
    }

    @Test
    public void test02HasUserWithEmail() throws Exception {
        Assert.assertTrue(userDAO.hasUserWithEmail(testUser.getEmail()));
    }

    @Test
    public void test03GetByEmailAndPassword() throws Exception {
        Assert.assertNotNull(userDAO.getByEmailAndPassword(testUser.getEmail(),testUser.getPassword()));
    }

    @Test
    public void test04UpdateUserStatus() throws Exception {
        Assert.assertTrue(userDAO.updateUserStatus((int)testUser.getIdUser(),"blocked"));
    }

    @Test
    public void test05GetById() throws Exception {
        Assert.assertNotNull(userDAO.getById((int)testUser.getIdUser()));
    }

    @Test
    public void test06GetAll() throws Exception {
        Assert.assertNotNull(userDAO.getAll());
    }

    @Test
    public void test07Delete() throws Exception {
        Assert.assertTrue(userDAO.delete((int)testUser.getIdUser()));
    }

}