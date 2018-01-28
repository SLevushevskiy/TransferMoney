package ua.nure.levushevskiy.SummaryTask4.dao.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ua.nure.levushevskiy.SummaryTask4.dao.api.PaymentDAO;
import ua.nure.levushevskiy.SummaryTask4.db.ConnectionPool;
import ua.nure.levushevskiy.SummaryTask4.entity.Payment;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaymentDAOImplTest {

    private static PaymentDAO paymentDAO = null;
    private static Payment testPayment = null;

    @BeforeClass
    public static void beforeClass() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("dataBase");
        ConnectionPool dataSource = null;
        try {
            dataSource = new ConnectionPool(resourceBundle);
        } catch (IOException | SQLException | PropertyVetoException e) {
            throw new IllegalArgumentException("Incorrect database properties!");
        }

        paymentDAO = new PaymentDAOImpl(dataSource);
        testPayment = new Payment(new Date(System.currentTimeMillis()),2.07,"Test payment",1,1, 1);

    }

    @Test
    public void test01Save() throws Exception {
        Assert.assertNotNull(paymentDAO.save(testPayment));
    }

    @Test
    public void test02GetById() throws Exception {
        Assert.assertNotNull(paymentDAO.getById((int)testPayment.getIdPayment()));
    }

    @Test
    public void test03UpdatePaymentStatus() throws Exception {
        Assert.assertNotNull(paymentDAO.updatePaymentStatus((int)testPayment.getIdPayment(),"prepared"));
    }

    @Test
    public void test04GetAll() throws Exception {
        Assert.assertNotNull(paymentDAO.getAll());
    }

    @Test
    public void test05Delete() throws Exception {
        Assert.assertNotNull(paymentDAO.delete((int)testPayment.getIdPayment()));
    }

}