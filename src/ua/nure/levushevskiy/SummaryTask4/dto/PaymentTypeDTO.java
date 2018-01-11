package ua.nure.levushevskiy.SummaryTask4.dto;

public class PaymentTypeDTO {

    private long idPaymentType;

    private String type;

    public PaymentTypeDTO() {
    }

    public PaymentTypeDTO(long idPaymentType, String type) {
        this.idPaymentType = idPaymentType;
        this.type = type;
    }

    public long getIdPaymentType() {
        return idPaymentType;
    }

    public void setIdPaymentType(long idPaymentType) {
        this.idPaymentType = idPaymentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentTypeDTO that = (PaymentTypeDTO) o;

        if (idPaymentType != that.idPaymentType) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPaymentType ^ (idPaymentType >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PaymentTypeDTO{" +
                "idPaymentType=" + idPaymentType +
                ", type='" + type + '\'' +
                '}';
    }
}
