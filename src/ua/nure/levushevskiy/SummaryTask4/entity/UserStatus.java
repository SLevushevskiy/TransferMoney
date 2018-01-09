package ua.nure.levushevskiy.SummaryTask4.entity;

public class UserStatus {

    private long idUserStatus;

    private String status;

    public UserStatus(long idUserStatus, String status) {
        this.idUserStatus = idUserStatus;
        this.status = status;
    }

    public UserStatus() {
    }

    public long getIdUserStatus() {
        return idUserStatus;
    }

    public void setIdUserStatus(long idUserStatus) {
        this.idUserStatus = idUserStatus;
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

        UserStatus that = (UserStatus) o;

        if (idUserStatus != that.idUserStatus) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUserStatus ^ (idUserStatus >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "idUserStatus=" + idUserStatus +
                ", status='" + status + '\'' +
                '}';
    }
}
