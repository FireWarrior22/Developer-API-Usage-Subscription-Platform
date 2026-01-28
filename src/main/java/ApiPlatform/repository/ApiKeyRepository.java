package ApiPlatform.repository;
import ApiPlatform.domain.apikey.ApiKey;
import ApiPlatform.domain.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

    Optional<ApiKey> findByKeyHashAndActiveTrue(String keyHash);

    List<ApiKey> findByOrganization(Organization organization);
}

