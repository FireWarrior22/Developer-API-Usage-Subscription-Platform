package ApiPlatform.repository;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.subscription.Subscription;
import ApiPlatform.domain.subscription.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByOrganization(Organization organization);

    Optional<Subscription> findByOrganizationAndStatus(
            Organization organization,
            SubscriptionStatus status
    );
}

