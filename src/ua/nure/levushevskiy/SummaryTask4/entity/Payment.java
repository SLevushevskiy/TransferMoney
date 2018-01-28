package ua.nure.levushevskiy.SummaryTask4.entity;

import java.sql.Date;

public class Payment {

    private long idPayment;

    private Date datePayment;

    private double total;

    private String description;

    private long accountId;

    private long statusId;

    private long paymentNameId;

    public Payment() {
    }

    public Payment(Date datePayment, double total, String description, long accountId, long statusId, long paymentNameId) {
        this.datePayment = datePayment;
        this.total = total;
        this.description = description;
        this.accountId = accountId;
        this.statusId = statusId;
        this.paymentNameId = paymentNameId;
    }

    public long getPaymentNameId() {
        return paymentNameId;
    }

    public void setPaymentNameId(long paymentNameId) {
        this.paymentNameId = paymentNameId;
    }

    public Payment(long idPayment, Date datePayment, double total, String description, long accountId, long statusId, long paymentNameId) {

        this.idPayment = idPayment;
        this.datePayment = datePayment;
        this.total = total;
        this.description = description;
        this.accountId = accountId;
        this.statusId = statusId;
        this.paymentNameId = paymentNameId;
    }

    public long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(long idPayment) {
        this.idPayment = idPayment;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (idPayment != payment.idPayment) return false;
        if (Double.compare(payment.total, total) != 0) return false;
        if (accountId != payment.accountId) return false;
        if (statusId != payment.statusId) return false;
        if (paymentNameId != payment.paymentNameId) return false;
        if (datePayment != null ? !datePayment.equals(payment.datePayment) : payment.datePayment != null) return false;
        return description != null ? description.equals(payment.description) : payment.description == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idPayment ^ (idPayment >>> 32));
        result = 31 * result + (datePayment != null ? datePayment.hashCode() : 0);
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (int) (statusId ^ (statusId >>> 32));
        result = 31 * result + (int) (paymentNameId ^ (paymentNameId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + idPayment +
                ", datePayment=" + datePayment +
                ", total=" + total +
                ", description='" + description + '\'' +
                ", accountId=" + accountId +
                ", statusId=" + statusId +
                ", paymentNameId=" + paymentNameId +
                '}';
    }
}