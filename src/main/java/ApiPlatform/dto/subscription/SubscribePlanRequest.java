package ApiPlatform.dto.subscription;
import jakarta.validation.constraints.NotBlank;

public class SubscribePlanRequest {

    @NotBlank
    private String planCode;

    protected SubscribePlanRequest() {
    }

    public SubscribePlanRequest(String planCode) {
        this.planCode = planCode;
    }

    public String getPlanCode() {
        return planCode;
    }
}
