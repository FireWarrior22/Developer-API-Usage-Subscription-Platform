package ApiPlatform.service.subscription;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.subscription.Plan;
import ApiPlatform.domain.subscription.Subscription;
import ApiPlatform.dto.subscription.SubscribePlanRequest;
import ApiPlatform.repository.OrganizationRepository;
import ApiPlatform.repository.PlanRepository;
import ApiPlatform.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;
    private final OrganizationRepository organizationRepository;

    public SubscriptionService(
            SubscriptionRepository subscriptionRepository,
            PlanRepository planRepository,
            OrganizationRepository organizationRepository
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.planRepository = planRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public void subscribe(SubscribePlanRequest request) {

        Organization organization = organizationRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(); // placeholder (security context later)

        Plan plan = planRepository.findByCode(request.getPlanCode())
                .orElseThrow(() -> new RuntimeException("Invalid plan"));

        subscriptionRepository.findByOrganization(organization)
                .ifPresent(subscriptionRepository::delete);

        Subscription subscription = new Subscription(
                organization,
                plan,
                LocalDate.now(),
                LocalDate.now().plusMonths(1)
        );

        subscriptionRepository.save(subscription);
    }
}

