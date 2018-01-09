package ua.nure.levushevskiy.SummaryTask4.dto;

public class UserStatusDTO {

    private long idUserStatusDTO;

    private String status;

    public UserStatusDTO(final long idUserStatusDTO, final String status) {
        this.idUserStatusDTO = idUserStatusDTO;
        this.status = status;
    }

    public UserStatusDTO() {
    }

    public long getIdUserStatusDTO() {
        return idUserStatusDTO;
    }

    public void setIdUserStatusDTO(final long idUserStatusDTO) {
        this.idUserStatusDTO = idUserStatusDTO;
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

        UserStatusDTO that = (UserStatusDTO) o;

        if (idUserStatusDTO != that.idUserStatusDTO) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUserStatusDTO ^ (idUserStatusDTO >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserStatusDTO{" +
                "idUserStatusDTO=" + idUserStatusDTO +
                ", status='" + status + '\'' +
                '}';
    }
}
