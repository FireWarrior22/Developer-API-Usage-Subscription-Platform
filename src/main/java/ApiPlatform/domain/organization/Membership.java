package ApiPlatform.domain.organization;
import ApiPlatform.domain.user.Role;
import ApiPlatform.domain.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "memberships",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "user_id")
        }
)
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false, updatable = false)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Membership() {
    }

    public Membership(User user, Organization organization, Role role) {
        if (user == null) {
            throw new IllegalArgumentException("User is required");
        }
        if (organization == null) {
            throw new IllegalArgumentException("Organization is required");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role is required");
        }

        this.user = user;
        this.organization = organization;
        this.role = role;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isAdmin() {
        return this.role == Role.ORG_ADMIN;
    }

    public void changeRole(Role newRole) {
        if (newRole == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = newRole;
    }
}