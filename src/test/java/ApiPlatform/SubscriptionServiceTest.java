package ApiPlatform;

import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.subscription.Plan;
import ApiPlatform.domain.subscription.Subscription;
import ApiPlatform.dto.subscription.SubscribePlanRequest;
import ApiPlatform.repository.OrganizationRepository;
import ApiPlatform.repository.PlanRepository;
import ApiPlatform.repository.SubscriptionRepository;
import ApiPlatform.service.subscription.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private PlanRepository planRepository;

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private SubscriptionService subscriptionService;

    @Test
    void subscribe_replacesExistingSubscription() {

        Organization org = new Organization("Org");
        Plan plan = new Plan("PRO", "Pro Plan", 10000L, 999.0);

        when(organizationRepository.findAll())
                .thenReturn(List.of(org));

        when(planRepository.findByCode("PRO"))
                .thenReturn(Optional.of(plan));

        when(subscriptionRepository.findByOrganization(org))
                .thenReturn(Optional.of(mock(Subscription.class)));

        subscriptionService.subscribe(
                new SubscribePlanRequest("PRO")
        );

        verify(subscriptionRepository).delete(any());
        verify(subscriptionRepository).save(any(Subscription.class));
    }
}
