package ApiPlatform.service.usage;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.subscription.Subscription;
import ApiPlatform.domain.usage.UsageAggregate;
import ApiPlatform.dto.usage.UsageSummaryResponse;
import ApiPlatform.repository.OrganizationRepository;
import ApiPlatform.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public class UsageService {

    private final UsageAggregationService aggregationService;
    private final SubscriptionRepository subscriptionRepository;
    private final OrganizationRepository organizationRepository;

    public UsageService(
            UsageAggregationService aggregationService,
            SubscriptionRepository subscriptionRepository,
            OrganizationRepository organizationRepository
    ) {
        this.aggregationService = aggregationService;
        this.subscriptionRepository = subscriptionRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public UsageSummaryResponse getCurrentUsage() {

        Organization organization = organizationRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(); // placeholder

        Subscription subscription = subscriptionRepository
                .findByOrganization(organization)
                .orElseThrow();

        YearMonth now = YearMonth.now();

        UsageAggregate aggregate = aggregationService.incrementUsage(organization);

        long limit = subscription.getPlan().getMonthlyApiLimit();

        return new UsageSummaryResponse(
                now,
                aggregate.getApiCallCount(),
                limit
        );
    }
}

