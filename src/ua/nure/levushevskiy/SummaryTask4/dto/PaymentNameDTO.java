package ua.nure.levushevskiy.SummaryTask4.dto;

public class PaymentNameDTO {

    private long idPaymentName;

    private String paymentName;

    private PaymentTypeDTO paymentTypeDTO;

    public PaymentNameDTO() {
        paymentTypeDTO = new PaymentTypeDTO();
        paymentTypeDTO.setIdPaymentType(1);
    }

    public PaymentNameDTO(long idPaymentName, String paymentName, PaymentTypeDTO paymentTypeDTO) {

        this.idPaymentName = idPaymentName;
        this.paymentName = paymentName;
        this.paymentTypeDTO = paymentTypeDTO;
    }

    public long getIdPaymentName() {

        return idPaymentName;
    }

    public void setIdPaymentName(long idPaymentName) {
        this.idPaymentName = idPaymentName;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public PaymentTypeDTO getPaymentTypeDTO() {
        return paymentTypeDTO;
    }

    public void setPaymentTypeDTO(PaymentTypeDTO paymentTypeDTO) {
        this.paymentTypeDTO = paymentTypeDTO;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentNameDTO that = (PaymentNameDTO) o;

        if (idPaymentName != that.idPaymentName) return false;
        if (paymentName != null ? !paymentName.equals(that.paymentName) : that.paymentName != null) return false;
        return paymentTypeDTO != null ? paymentTypeDTO.equals(that.paymentTypeDTO) : that.paymentTypeDTO == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPaymentName ^ (idPaymentName >>> 32));
        result = 31 * result + (paymentName != null ? paymentName.hashCode() : 0);
        result = 31 * result + (paymentTypeDTO != null ? paymentTypeDTO.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PaymentNameDTO{" +
                "idPaymentName=" + idPaymentName +
                ", paymentName='" + paymentName + '\'' +
                ", paymentTypeDTO=" + paymentTypeDTO +
                '}';
    }
}
