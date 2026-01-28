package ApiPlatform.domain.apikey;
import ApiPlatform.domain.organization.Organization;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "api_keys",
        indexes = {
                @Index(name = "idx_api_key_hash", columnList = "keyHash")
        }
)
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Owning organization
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    // Hashed value of the API key
    @Column(nullable = false, unique = true)
    private String keyHash;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime revokedAt;

    protected ApiKey() {
    }

    public ApiKey(Organization organization, String keyHash) {
        this.organization = organization;
        this.keyHash = keyHash;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public String getKeyHash() {
        return keyHash;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getRevokedAt() {
        return revokedAt;
    }

    public void revoke() {
        this.active = false;
        this.revokedAt = LocalDateTime.now();
    }
}

