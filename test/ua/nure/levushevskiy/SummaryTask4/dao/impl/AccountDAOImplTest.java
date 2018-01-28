package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ua.nure.levushevskiy.SummaryTask4.dao.api.AccountDAO;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.Account;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountDAOImplTest {

    private static AccountDAO accountDAO = null;
    private static Account testAccount = null;

    @BeforeClass
    public static void beforeClass() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("dataBase");
        ConnectionPool dataSource = null;
        try {
            dataSource = new ConnectionPool(resourceBundle);
        } catch (IOException | SQLException | PropertyVetoException e) {
            throw new IllegalArgumentException("Incorrect database properties!");
        }

        accountDAO = new AccountDAOImpl(dataSource);
        testAccount = new Account(123.25,new Date(System.currentTimeMillis()),1,1,1);

    }

    @Test
    public void test01Save() throws Exception {
        Assert.assertNotNull(accountDAO.save(testAccount));
    }

    @Test
    public void test02UpdateAccountStatus() throws Exception {
        Assert.assertTrue(accountDAO.updateAccountStatus((int)testAccount.getIdAccount(),"blocked"));
    }

    @Test
    public void test03ChangeAccountAmound() throws Exception {
        Assert.assertTrue(accountDAO.changeAccountAmound((int)testAccount.getIdAccount(),25.25));
    }


    @Test
    public void test04GetById() throws Exception {
        Assert.assertNotNull(accountDAO.getById((int)testAccount.getIdAccount()));
    }

    @Test
    public void test05GetAll() throws Exception {
        Assert.assertNotNull(accountDAO.getAll());
    }

    @Test
    public void test06GetAllByDate() throws Exception {
        Assert.assertNotNull(accountDAO.getAllByDate(testAccount.getEndDate()));
    }

    @Test
    public void test07Delete() throws Exception {
        Assert.assertTrue(accountDAO.delete((int)testAccount.getIdAccount()));
    }
}