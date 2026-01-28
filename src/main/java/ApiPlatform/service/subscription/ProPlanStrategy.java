package ApiPlatform.service.subscription;
import ApiPlatform.domain.subscription.Plan;
import org.springframework.stereotype.Component;

@Component("PRO")
public class ProPlanStrategy implements PricingStrategy {

    @Override
    public double calculateMonthlyCharge(long apiCallsUsed, Plan plan) {
        return plan.getMonthlyPrice();
    }
}

