package ApiPlatform.service.subscription;
import ApiPlatform.domain.subscription.Plan;
import org.springframework.stereotype.Component;

@Component("FREE")
public class FreePlanStrategy implements PricingStrategy {

    @Override
    public double calculateMonthlyCharge(long apiCallsUsed, Plan plan) {
        return 0.0;
    }
}

