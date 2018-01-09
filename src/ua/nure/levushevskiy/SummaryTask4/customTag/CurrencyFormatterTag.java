package ua.nure.levushevskiy.SummaryTask4.customTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Custom tag for formatted currency output.
 */
public class CurrencyFormatterTag extends SimpleTagSupport {

    /**
     * Format of value.
     */
    private String format;

    /**
     * Currency.
     */
    private String currency;

    /**
     * Input value.
     */
    private String value;

    /**
     * Default constructor.
     */
    public CurrencyFormatterTag() {
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public final void doTag() throws JspException, IOException {
        try {
            double amount = Double.parseDouble(value);
            DecimalFormat formatter = new DecimalFormat(format);
            String formattedNumber = formatter.format(amount);
            getJspContext().getOut().write(currency + formattedNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SkipPageException("Exception in formatting " + value
                    + " with format " + format + " and currency: " + currency);
        }
    }
}
