package ApiPlatform.domain.subscription;
import jakarta.persistence.*;

@Entity
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long monthlyApiLimit;

    @Column(nullable = false)
    private Double monthlyPrice;

    protected Plan() {
    }

    public Plan(String code, String name, Long monthlyApiLimit, Double monthlyPrice) {
        this.code = code;
        this.name = name;
        this.monthlyApiLimit = monthlyApiLimit;
        this.monthlyPrice = monthlyPrice;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Long getMonthlyApiLimit() {
        return monthlyApiLimit;
    }

    public Double getMonthlyPrice() {
        return monthlyPrice;
    }

    public boolean isFreePlan() {
        return this.monthlyPrice == 0;
    }
}

