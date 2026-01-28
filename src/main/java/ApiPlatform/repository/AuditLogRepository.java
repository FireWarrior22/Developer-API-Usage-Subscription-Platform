package ApiPlatform.repository;
import ApiPlatform.domain.audit.AuditLog;
import ApiPlatform.domain.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByOrganizationOrderByTimestampDesc(
            Organization organization
    );
}

