package ApiPlatform.controller.organization;
import ApiPlatform.dto.organization.CreateOrganizationRequest;
import ApiPlatform.service.organization.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrganization(
            @Valid @RequestBody CreateOrganizationRequest request
    ) {
        organizationService.createOrganization(request);
        return ResponseEntity.ok().build();
    }
}

