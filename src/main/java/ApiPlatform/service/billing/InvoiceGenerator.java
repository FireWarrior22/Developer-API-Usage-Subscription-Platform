package ApiPlatform.service.billing;
import ApiPlatform.domain.billing.Invoice;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.subscription.Subscription;
import ApiPlatform.domain.usage.UsageAggregate;
import ApiPlatform.repository.SubscriptionRepository;
import ApiPlatform.repository.UsageAggregateRepository;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class InvoiceGenerator {

    private final SubscriptionRepository subscriptionRepository;
    private final UsageAggregateRepository usageAggregateRepository;

    public InvoiceGenerator(
            SubscriptionRepository subscriptionRepository,
            UsageAggregateRepository usageAggregateRepository
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.usageAggregateRepository = usageAggregateRepository;
    }

    public Invoice generate(Organization organization, YearMonth period) {

        Subscription subscription = subscriptionRepository.findByOrganization(organization)
                .orElseThrow();

        UsageAggregate usage = usageAggregateRepository
                .findByOrganizationAndYearAndMonth(
                        organization,
                        period.getYear(),
                        period.getMonthValue()
                )
                .orElseThrow();

        double amount = subscription.getPlan().getMonthlyPrice();

        return new Invoice(
                organization,
                subscription,
                period,
                usage.getApiCallCount(),
                amount
        );
    }
}

