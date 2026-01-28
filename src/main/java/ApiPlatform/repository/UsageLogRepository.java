package ApiPlatform.repository;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.usage.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {

    List<UsageLog> findByOrganizationAndTimestampBetween(
            Organization organization,
            LocalDateTime start,
            LocalDateTime end
    );
}
