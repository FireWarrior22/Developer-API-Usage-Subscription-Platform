package ApiPlatform.dto.organization;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateOrganizationRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    protected CreateOrganizationRequest() {
    }

    public CreateOrganizationRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
