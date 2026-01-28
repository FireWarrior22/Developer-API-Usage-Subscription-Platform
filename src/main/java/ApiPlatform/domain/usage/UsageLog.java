package ApiPlatform.domain.usage;
import ApiPlatform.domain.organization.Organization;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "usage_logs",
        indexes = {
                @Index(name = "idx_usage_org_time", columnList = "organization_id, timestamp")
        }
)
public class UsageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(nullable = false)
    private String endpoint;

    // Optional: HTTP method (GET, POST, etc.)
    @Column(nullable = false)
    private String httpMethod;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    protected UsageLog() {
    }

    public UsageLog(Organization organization, String endpoint, String httpMethod) {
        this.organization = organization;
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.timestamp = LocalDateTime.now();
    }


    public Long getId() {
        return id;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
