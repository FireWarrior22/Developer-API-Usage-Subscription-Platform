package ApiPlatform.domain.billing;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.subscription.Subscription;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.YearMonth;


@Entity
@Table(
        name = "invoices",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"organization_id", "year", "month"})
        }
)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private long totalApiCalls;

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDate generatedDate;

    protected Invoice() {
    }

    public Invoice(
            Organization organization,
            Subscription subscription,
            YearMonth period,
            long totalApiCalls,
            double amount
    ) {
        this.organization = organization;
        this.subscription = subscription;
        this.year = period.getYear();
        this.month = period.getMonthValue();
        this.totalApiCalls = totalApiCalls;
        this.amount = amount;
        this.status = InvoiceStatus.GENERATED;
        this.generatedDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public long getTotalApiCalls() {
        return totalApiCalls;
    }

    public double getAmount() {
        return amount;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;
    }


    public void markPaid() {
        this.status = InvoiceStatus.PAID;
    }

    public void markFailed() {
        this.status = InvoiceStatus.FAILED;
    }

    public YearMonth getPeriod() {
        return YearMonth.of(year, month);
    }
}
