package ua.nure.levushevskiy.SummaryTask4.dto;

import java.sql.Date;

public class PaymentDTO {

    private long idPayment;

    private Date datePayment;

    private Double total;

    private String description;

    private AccountDTO accountDTO;

    private PaymentStatusDTO paymentStatusDTO;

    private PaymentNameDTO paymentNameDTO;

    public PaymentDTO(long idPayment, Date datePayment, Double total, String description, AccountDTO accountDTO, PaymentStatusDTO paymentStatusDTO, PaymentNameDTO paymentNameDTO) {
        this.idPayment = idPayment;
        this.datePayment = datePayment;
        this.total = total;
        this.description = description;
        this.accountDTO = accountDTO;
        this.paymentStatusDTO = paymentStatusDTO;
        this.paymentNameDTO = paymentNameDTO;
    }

    public PaymentDTO() {
        paymentStatusDTO = new PaymentStatusDTO();
        paymentStatusDTO.setIdPaymentStatus(1);
    }

    public PaymentDTO(AccountDTO accountDTO) {
        super();
        this.accountDTO = accountDTO;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public PaymentStatusDTO getPaymentStatusDTO() {
        return paymentStatusDTO;
    }

    public void setPaymentStatusDTO(PaymentStatusDTO paymentStatusDTO) {
        this.paymentStatusDTO = paymentStatusDTO;
    }

    public PaymentNameDTO getPaymentNameDTO() {
        return paymentNameDTO;
    }

    public void setPaymentNameDTO(PaymentNameDTO paymentNameDTO) {
        this.paymentNameDTO = paymentNameDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentDTO that = (PaymentDTO) o;

        if (idPayment != that.idPayment) return false;
        if (datePayment != null ? !datePayment.equals(that.datePayment) : that.datePayment != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (accountDTO != null ? !accountDTO.equals(that.accountDTO) : that.accountDTO != null) return false;
        if (paymentStatusDTO != null ? !paymentStatusDTO.equals(that.paymentStatusDTO) : that.paymentStatusDTO != null)
            return false;
        return paymentNameDTO != null ? paymentNameDTO.equals(that.paymentNameDTO) : that.paymentNameDTO == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPayment ^ (idPayment >>> 32));
        result = 31 * result + (datePayment != null ? datePayment.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (accountDTO != null ? accountDTO.hashCode() : 0);
        result = 31 * result + (paymentStatusDTO != null ? paymentStatusDTO.hashCode() : 0);
        result = 31 * result + (paymentNameDTO != null ? paymentNameDTO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "idPayment=" + idPayment +
                ", datePayment=" + datePayment +
                ", total=" + total +
                ", description='" + description + '\'' +
                ", accountDTO=" + accountDTO +
                ", paymentStatusDTO=" + paymentStatusDTO +
                ", paymentNameDTO=" + paymentNameDTO +
                '}';
    }
}
