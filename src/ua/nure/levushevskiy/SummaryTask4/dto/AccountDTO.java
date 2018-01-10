package ua.nure.levushevskiy.SummaryTask4.dto;

public class AccountDTO {
    private long idAccount;

    private double amound;

    private UserDTO userDTO;

    private AccountStatusDTO accountStatusDTO;

    private AccountNameDTO accountNameDTO;

    public AccountDTO(final long idAccount,final double amound,final UserDTO userDTO,final AccountStatusDTO accountStatusDTO,final AccountNameDTO accountNameDTO) {
        this.idAccount = idAccount;
        this.amound = amound;
        this.userDTO = userDTO;
        this.accountStatusDTO = accountStatusDTO;
        this.accountNameDTO = accountNameDTO;
    }

    public AccountDTO() {
        accountNameDTO = new AccountNameDTO();
        accountNameDTO.setIdAccountName(1);
        accountStatusDTO = new AccountStatusDTO();
        accountStatusDTO.setIdAcccountStatus(1);
    }

    public AccountDTO(final UserDTO userDTO) {
        super();
        this.userDTO = userDTO;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(final long idAccount) {
        this.idAccount = idAccount;
    }

    public double getAmound() {
        return amound;
    }

    public void setAmound(final double amound) {
        this.amound = amound;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(final UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public AccountStatusDTO getAccountStatusDTO() {
        return accountStatusDTO;
    }

    public void setAccountStatusDTO(final AccountStatusDTO accountStatusDTO) {
        this.accountStatusDTO = accountStatusDTO;
    }

    public AccountNameDTO getAccountNameDTO() {
        return accountNameDTO;
    }

    public void setAccountNameDTO(final AccountNameDTO accountNameDTO) {
        this.accountNameDTO = accountNameDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountDTO that = (AccountDTO) o;

        if (idAccount != that.idAccount) return false;
        if (Double.compare(that.amound, amound) != 0) return false;
        if (userDTO != null ? !userDTO.equals(that.userDTO) : that.userDTO != null) return false;
        if (accountStatusDTO != null ? !accountStatusDTO.equals(that.accountStatusDTO) : that.accountStatusDTO != null)
            return false;
        return accountNameDTO != null ? accountNameDTO.equals(that.accountNameDTO) : that.accountNameDTO == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idAccount ^ (idAccount >>> 32));
        temp = Double.doubleToLongBits(amound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (userDTO != null ? userDTO.hashCode() : 0);
        result = 31 * result + (accountStatusDTO != null ? accountStatusDTO.hashCode() : 0);
        result = 31 * result + (accountNameDTO != null ? accountNameDTO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "idAccount=" + idAccount +
                ", amound=" + amound +
                ", userDTO=" + userDTO +
                ", accountStatusDTO=" + accountStatusDTO +
                ", accountNameDTO=" + accountNameDTO +
                '}';
    }
}
