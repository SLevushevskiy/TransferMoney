package ua.nure.levushevskiy.SummaryTask4.dto;

public class AccountStatusDTO {

    private long idAcccountStatus;

    private String status;

    public AccountStatusDTO() {
    }

    public AccountStatusDTO(final long idAcccountStatus, String status) {
        this.idAcccountStatus = idAcccountStatus;
        this.status = status;
    }

    public long getIdAcccountStatus() {
        return idAcccountStatus;
    }

    public void setIdAcccountStatus(final long idAcccountStatus) {
        this.idAcccountStatus = idAcccountStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountStatusDTO that = (AccountStatusDTO) o;

        if (idAcccountStatus != that.idAcccountStatus) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idAcccountStatus ^ (idAcccountStatus >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountStatusDTO{" +
                "idAcccountStatus=" + idAcccountStatus +
                ", status='" + status + '\'' +
                '}';
    }
}

