package ua.nure.levushevskiy.SummaryTask4.entity;

public class AccountName {
    private long idAccountName;

    private String name;

    public AccountName() {
    }

    public AccountName(long idAccountName, String name) {
        this.idAccountName = idAccountName;
        this.name = name;
    }

    public long getIdAccountName() {
        return idAccountName;
    }

    public void setIdAccountName(long idAccountName) {
        this.idAccountName = idAccountName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountName that = (AccountName) o;

        if (idAccountName != that.idAccountName) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idAccountName ^ (idAccountName >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountName{" +
                "idAccountName=" + idAccountName +
                ", name='" + name + '\'' +
                '}';
    }
}
