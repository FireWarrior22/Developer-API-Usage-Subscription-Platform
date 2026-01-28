package ApiPlatform.service.billing;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.repository.InvoiceRepository;
import ApiPlatform.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;

@Service
public class BillingService {

    private final InvoiceRepository invoiceRepository;
    private final OrganizationRepository organizationRepository;
    private final InvoiceGenerator invoiceGenerator;

    public BillingService(
            InvoiceRepository invoiceRepository,
            OrganizationRepository organizationRepository,
            InvoiceGenerator invoiceGenerator
    ) {
        this.invoiceRepository = invoiceRepository;
        this.organizationRepository = organizationRepository;
        this.invoiceGenerator = invoiceGenerator;
    }

    @Transactional
    public void generateMonthlyInvoices() {

        YearMonth period = YearMonth.now().minusMonths(1);

        for (Organization org : organizationRepository.findAll()) {

            invoiceRepository.findByOrganizationAndYearAndMonth(
                    org,
                    period.getYear(),
                    period.getMonthValue()
            ).ifPresentOrElse(
                    i -> {}, // already billed
                    () -> invoiceRepository.save(
                            invoiceGenerator.generate(org, period)
                    )
            );
        }
    }
}
