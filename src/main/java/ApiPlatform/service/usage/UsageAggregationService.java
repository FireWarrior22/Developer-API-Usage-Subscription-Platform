package ApiPlatform.service.usage;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.usage.UsageAggregate;
import ApiPlatform.repository.UsageAggregateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;

@Service
public class UsageAggregationService {

    private final UsageAggregateRepository usageAggregateRepository;

    public UsageAggregationService(UsageAggregateRepository usageAggregateRepository) {
        this.usageAggregateRepository = usageAggregateRepository;
    }

    @Transactional
    public UsageAggregate incrementUsage(Organization organization) {

        YearMonth currentPeriod = YearMonth.now();

        UsageAggregate aggregate = usageAggregateRepository
                .findByOrganizationAndYearAndMonth(
                        organization,
                        currentPeriod.getYear(),
                        currentPeriod.getMonthValue()
                )
                .orElseGet(() -> {
                    UsageAggregate ua = new UsageAggregate(organization, currentPeriod);
                    return usageAggregateRepository.save(ua);
                });

        aggregate.increment();
        return aggregate;
    }
}
