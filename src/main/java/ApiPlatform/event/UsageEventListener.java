package ApiPlatform.event;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.usage.UsageLog;
import ApiPlatform.repository.UsageLogRepository;
import ApiPlatform.service.usage.UsageAggregationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsageEventListener {

    private final UsageAggregationService aggregationService;
    private final UsageLogRepository usageLogRepository;

    public UsageEventListener(
            UsageAggregationService aggregationService,
            UsageLogRepository usageLogRepository
    ) {
        this.aggregationService = aggregationService;
        this.usageLogRepository = usageLogRepository;
    }

    @Transactional
    @EventListener
    public void handleUsageEvent(UsageEvent event) {

        Organization organization = event.getOrganization();

        // 1️⃣ Increment monthly aggregate
        aggregationService.incrementUsage(organization);

        // 2️⃣ Persist usage log
        UsageLog log = new UsageLog(
                organization,
                event.getEndpoint(),
                event.getHttpMethod()
        );

        usageLogRepository.save(log);
    }
}

