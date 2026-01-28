package ApiPlatform.event;
import ApiPlatform.domain.organization.Organization;
import org.springframework.context.ApplicationEvent;

public class UsageEvent extends ApplicationEvent {

    private final Organization organization;
    private final String endpoint;
    private final String httpMethod;

    public UsageEvent(
            Object source,
            Organization organization,
            String endpoint,
            String httpMethod
    ) {
        super(source);
        this.organization = organization;
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
    }

    public Organization getOrganization() {
        return organization;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getHttpMethod() {
        return httpMethod;
    }
}
