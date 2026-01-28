package ApiPlatform.repository;
import ApiPlatform.domain.organization.Membership;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Optional<Membership> findByUserAndOrganization(User user, Organization organization);

    List<Membership> findByOrganization(Organization organization);

    boolean existsByUserAndOrganization(User user, Organization organization);
}

