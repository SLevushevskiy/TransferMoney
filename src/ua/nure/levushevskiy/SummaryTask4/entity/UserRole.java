package ua.nure.levushevskiy.SummaryTask4.entity;

public class UserRole {
    private long idUserRole;

    private String rank;

    public UserRole(long idUserRole, String rank) {
        this.idUserRole = idUserRole;
        this.rank = rank;
    }

    public UserRole() {
    }

    public long getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(long idUserRole) {
        this.idUserRole = idUserRole;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (idUserRole != userRole.idUserRole) return false;
        return rank != null ? rank.equals(userRole.rank) : userRole.rank == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idUserRole ^ (idUserRole >>> 32));
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "idUserRole=" + idUserRole +
                ", rank='" + rank + '\'' +
                '}';
    }
}

