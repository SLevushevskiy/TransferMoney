package ua.nure.levushevskiy.SummaryTask4.dto;

public class UserRoleDTO {

    private long idUserRole;

    private String rank;

    public UserRoleDTO(final long idUserRole,final String rank) {
        this.idUserRole = idUserRole;
        this.rank = rank;
    }

    public UserRoleDTO() {
    }

    public long getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(final long idUserRole) {
        this.idUserRole = idUserRole;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(final String rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleDTO that = (UserRoleDTO) o;

        if (idUserRole != that.idUserRole) return false;
        return rank != null ? rank.equals(that.rank) : that.rank == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUserRole ^ (idUserRole >>> 32));
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRoleDTO{" +
                "idUserRole=" + idUserRole +
                ", rank='" + rank + '\'' +
                '}';
    }
}
