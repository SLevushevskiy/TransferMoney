package ua.nure.levushevskiy.SummaryTask4.entity;

public class PaymentName {

    private long idPaymentName;

    private String paymentName;

    private long paymentTypeId;

    public PaymentName() {
    }

    public PaymentName(long idPaymentName, String paymentName, long paymentTypeId) {

        this.idPaymentName = idPaymentName;
        this.paymentName = paymentName;
        paymentTypeId = paymentTypeId;
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

    public long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(long paymentTypeId) {
        paymentTypeId = paymentTypeId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentName that = (PaymentName) o;

        if (idPaymentName != that.idPaymentName) return false;
        if (paymentTypeId != that.paymentTypeId) return false;
        return paymentName != null ? paymentName.equals(that.paymentName) : that.paymentName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPaymentName ^ (idPaymentName >>> 32));
        result = 31 * result + (paymentName != null ? paymentName.hashCode() : 0);
        result = 31 * result + (int) (paymentTypeId ^ (paymentTypeId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PaymentName{" +
                "idPaymentName=" + idPaymentName +
                ", paymentName='" + paymentName + '\'' +
                ", PaymentTypeId=" + paymentTypeId +
                '}';
    }
}
