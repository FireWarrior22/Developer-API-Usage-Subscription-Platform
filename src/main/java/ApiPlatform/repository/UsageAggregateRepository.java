package ApiPlatform.repository;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.usage.UsageAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsageAggregateRepository extends JpaRepository<UsageAggregate, Long> {

    Optional<UsageAggregate> findByOrganizationAndYearAndMonth(
            Organization organization,
            int year,
            int month
    );
}

