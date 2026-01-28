package ApiPlatform.scheduler;
import ApiPlatform.service.billing.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BillingScheduler {

    private static final Logger log = LoggerFactory.getLogger(BillingScheduler.class);

    private final BillingService billingService;

    public BillingScheduler(BillingService billingService) {
        this.billingService = billingService;
    }

    /**
     * Runs once every month to generate invoices for the previous month.
     *
     * Cron: 0 0 2 1 * ?
     * → At 02:00 AM on the 1st day of every month
     */
    @Scheduled(cron = "0 0 2 1 * ?")
    public void runMonthlyBilling() {

        log.info("Starting monthly billing job");

        try {
            billingService.generateMonthlyInvoices();
            log.info("Monthly billing job completed successfully");
        } catch (Exception ex) {
            log.error("Monthly billing job failed", ex);
        }
    }
}
