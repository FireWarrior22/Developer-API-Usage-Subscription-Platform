package ApiPlatform.controller.subscription;
import ApiPlatform.dto.subscription.SubscribePlanRequest;
import ApiPlatform.service.subscription.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<Void> subscribe(
            @Valid @RequestBody SubscribePlanRequest request
    ) {
        subscriptionService.subscribe(request);
        return ResponseEntity.ok().build();
    }
}

