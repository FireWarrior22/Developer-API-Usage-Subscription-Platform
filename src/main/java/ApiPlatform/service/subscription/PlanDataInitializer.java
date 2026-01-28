package ApiPlatform.service.subscription;
import ApiPlatform.domain.subscription.Plan;
import ApiPlatform.repository.PlanRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PlanDataInitializer {

    private final PlanRepository planRepository;

    public PlanDataInitializer(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @PostConstruct
    public void init() {

        if (planRepository.count() > 0) {
            return;
        }

        planRepository.save(new Plan("FREE", "Free Plan", 1000L, 0.0));
        planRepository.save(new Plan("PRO", "Pro Plan", 10000L, 999.0));
        planRepository.save(new Plan("ENTERPRISE", "Enterprise Plan", 100000L, 4999.0));
    }
}

