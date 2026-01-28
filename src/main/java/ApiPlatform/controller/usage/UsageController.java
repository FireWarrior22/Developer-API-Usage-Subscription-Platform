package ApiPlatform.controller.usage;
import ApiPlatform.dto.usage.UsageSummaryResponse;
import ApiPlatform.service.usage.UsageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usage")
public class UsageController {

    private final UsageService usageService;

    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }

    @GetMapping("/current")
    public ResponseEntity<UsageSummaryResponse> currentUsage() {
        return ResponseEntity.ok(usageService.getCurrentUsage());
    }
}

