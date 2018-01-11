package ua.nure.levushevskiy.SummaryTask4.dto;

public class PaymentStatusDTO {

    private long idPaymentStatus;

    private String status;

    public PaymentStatusDTO() {
    }

    public PaymentStatusDTO(long idPaymentStatus, String status) {
        this.idPaymentStatus = idPaymentStatus;
        this.status = status;
    }

    public long getIdPaymentStatus() {
        return idPaymentStatus;
    }

    public void setIdPaymentStatus(long idPaymentStatus) {
        this.idPaymentStatus = idPaymentStatus;
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

        PaymentStatusDTO that = (PaymentStatusDTO) o;

        if (idPaymentStatus != that.idPaymentStatus) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPaymentStatus ^ (idPaymentStatus >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PaymentStatusDTO{" +
                "idPaymentStatus=" + idPaymentStatus +
                ", status='" + status + '\'' +
                '}';
    }
}
