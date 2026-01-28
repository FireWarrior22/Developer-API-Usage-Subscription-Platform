package ApiPlatform.repository;
import ApiPlatform.domain.billing.Invoice;
import ApiPlatform.domain.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findByOrganizationAndYearAndMonth(
            Organization organization,
            int year,
            int month
    );

    List<Invoice> findByOrganizationOrderByYearDescMonthDesc(
            Organization organization
    );
}

