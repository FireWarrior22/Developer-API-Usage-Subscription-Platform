package ApiPlatform.domain.usage;
import ApiPlatform.domain.organization.Organization;
import jakarta.persistence.*;
import java.time.YearMonth;

@Entity
@Table(
        name = "usage_aggregates",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"organization_id", "year", "month"})
        }
)
public class UsageAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which organization
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private long apiCallCount;

    protected UsageAggregate() {
    }

    public UsageAggregate(Organization organization, YearMonth period) {
        this.organization = organization;
        this.year = period.getYear();
        this.month = period.getMonthValue();
        this.apiCallCount = 0;
    }


    public Long getId() {
        return id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public long getApiCallCount() {
        return apiCallCount;
    }


    public void increment() {
        this.apiCallCount++;
    }

    public YearMonth getPeriod() {
        return YearMonth.of(year, month);
    }
}
