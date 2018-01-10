package ua.nure.levushevskiy.SummaryTask4.entity;

public class AccountStatus {

    private long idAccountStatus;

    private String status;

    public AccountStatus() {
    }

    public AccountStatus(long idAccountStatus, String status) {
        this.idAccountStatus = idAccountStatus;
        this.status = status;
    }

    public long getIdAccountStatus() {
        return idAccountStatus;
    }

    public void setIdAccountStatus(long idAccountStatus) {
        this.idAccountStatus = idAccountStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountStatus that = (AccountStatus) o;

        if (idAccountStatus != that.idAccountStatus) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idAccountStatus ^ (idAccountStatus >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountStatus{" +
                "idAccountStatus=" + idAccountStatus +
                ", status='" + status + '\'' +
                '}';
    }
}
