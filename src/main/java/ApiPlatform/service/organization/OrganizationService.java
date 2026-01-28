package ApiPlatform.service.organization;
import ApiPlatform.domain.organization.Membership;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.user.Role;
import ApiPlatform.domain.user.User;
import ApiPlatform.dto.organization.CreateOrganizationRequest;
import ApiPlatform.repository.MembershipRepository;
import ApiPlatform.repository.OrganizationRepository;
import ApiPlatform.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;

    public OrganizationService(
            OrganizationRepository organizationRepository,
            MembershipRepository membershipRepository,
            UserRepository userRepository
    ) {
        this.organizationRepository = organizationRepository;
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createOrganization(CreateOrganizationRequest request) {

        if (organizationRepository.existsByName(request.getName())) {
            throw new RuntimeException("Organization already exists");
        }

        User currentUser = userRepository.findAll().stream().findFirst()
                .orElseThrow(); // placeholder (replace with SecurityContext)

        Organization organization = new Organization(request.getName());
        organizationRepository.save(organization);

        Membership membership = new Membership(
                currentUser,
                organization,
                Role.ORG_ADMIN
        );

        membershipRepository.save(membership);
    }
}

