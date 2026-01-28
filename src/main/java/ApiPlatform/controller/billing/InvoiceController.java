package ApiPlatform.controller.billing;
import ApiPlatform.dto.billing.InvoiceResponse;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.repository.InvoiceRepository;
import ApiPlatform.repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;
    private final OrganizationRepository organizationRepository;

    public InvoiceController(
            InvoiceRepository invoiceRepository,
            OrganizationRepository organizationRepository
    ) {
        this.invoiceRepository = invoiceRepository;
        this.organizationRepository = organizationRepository;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> listInvoices() {

        Organization org = organizationRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(); // placeholder (SecurityContext later)

        List<InvoiceResponse> invoices = invoiceRepository
                .findByOrganizationOrderByYearDescMonthDesc(org)
                .stream()
                .map(i -> new InvoiceResponse(
                        i.getId(),
                        i.getPeriod(),
                        i.getTotalApiCalls(),
                        i.getAmount(),
                        i.getStatus()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(invoices);
    }
}
