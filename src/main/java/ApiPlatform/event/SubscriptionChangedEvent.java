package ApiPlatform.event;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.subscription.Plan;
import org.springframework.context.ApplicationEvent;

public class SubscriptionChangedEvent extends ApplicationEvent {

    private final Organization organization;
    private final Plan oldPlan;
    private final Plan newPlan;

    public SubscriptionChangedEvent(
            Object source,
            Organization organization,
            Plan oldPlan,
            Plan newPlan
    ) {
        super(source);
        this.organization = organization;
        this.oldPlan = oldPlan;
        this.newPlan = newPlan;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Plan getOldPlan() {
        return oldPlan;
    }

    public Plan getNewPlan() {
        return newPlan;
    }
}

