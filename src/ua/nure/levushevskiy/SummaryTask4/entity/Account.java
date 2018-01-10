package ua.nure.levushevskiy.SummaryTask4.entity;

import java.sql.Date;

public class Account {
    private long idAccount;

    private double amound;

    private Date endDate;

    private long accountUserId;

    private long accountStatusId;

    private long accountNameId;

    public Account() {
    }

    public Account(long idAccount, double amound, Date endDate, long accountUserId, long accountStatusId, long accountNameId) {
        this.idAccount = idAccount;
        this.amound = amound;
        this.endDate = endDate;
        this.accountUserId = accountUserId;
        this.accountStatusId = accountStatusId;
        this.accountNameId = accountNameId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getIdAccount() {

        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public double getAmound() {
        return amound;
    }

    public void setAmound(double amound) {
        this.amound = amound;
    }

    public long getAccountUserId() {
        return accountUserId;
    }

    public void setAccountUserId(long accountUserId) {
        this.accountUserId = accountUserId;
    }

    public long getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(long accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    public long getAccountNameId() {
        return accountNameId;
    }

    public void setAccountNameId(long accountNameId) {
        this.accountNameId = accountNameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (idAccount != account.idAccount) return false;
        if (Double.compare(account.amound, amound) != 0) return false;
        if (accountUserId != account.accountUserId) return false;
        if (accountStatusId != account.accountStatusId) return false;
        if (accountNameId != account.accountNameId) return false;
        return endDate != null ? endDate.equals(account.endDate) : account.endDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idAccount ^ (idAccount >>> 32));
        temp = Double.doubleToLongBits(amound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (int) (accountUserId ^ (accountUserId >>> 32));
        result = 31 * result + (int) (accountStatusId ^ (accountStatusId >>> 32));
        result = 31 * result + (int) (accountNameId ^ (accountNameId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", amound=" + amound +
                ", endDate=" + endDate +
                ", accountUserId=" + accountUserId +
                ", accountStatusId=" + accountStatusId +
                ", accountNameId=" + accountNameId +
                '}';
    }
}
