package ApiPlatform.dto.billing;
import java.time.YearMonth;
import ApiPlatform.domain.billing.InvoiceStatus;

public class InvoiceResponse {

    private Long invoiceId;
    private YearMonth period;
    private long totalApiCalls;
    private double amount;
    private InvoiceStatus status;

    public InvoiceResponse(
            Long invoiceId,
            YearMonth period,
            long totalApiCalls,
            double amount,
            InvoiceStatus status
    ) {
        this.invoiceId = invoiceId;
        this.period = period;
        this.totalApiCalls = totalApiCalls;
        this.amount = amount;
        this.status = status;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public YearMonth getPeriod() {
        return period;
    }

    public long getTotalApiCalls() {
        return totalApiCalls;
    }

    public double getAmount() {
        return amount;
    }

    public InvoiceStatus getStatus() {
        return status;
    }
}
