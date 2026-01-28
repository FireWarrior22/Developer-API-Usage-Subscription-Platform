package ApiPlatform.service.subscription;


import ApiPlatform.domain.subscription.Plan;

public interface PricingStrategy {
    double calculateMonthlyCharge(long apiCallsUsed, Plan plan);

}
