package ApiPlatform;

import ApiPlatform.domain.organization.Organization;
import ApiPlatform.domain.usage.UsageAggregate;
import ApiPlatform.repository.UsageAggregateRepository;
import ApiPlatform.service.usage.UsageAggregationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsageAggregationServiceTest {

    @Mock
    private UsageAggregateRepository usageAggregateRepository;

    @InjectMocks
    private UsageAggregationService service;

    @Test
    void incrementUsage_createsAggregateIfMissing() {

        Organization org = new Organization("Org");

        when(usageAggregateRepository
                .findByOrganizationAndYearAndMonth(any(), anyInt(), anyInt()))
                .thenReturn(Optional.empty());

        UsageAggregate result = service.incrementUsage(org);

        assertEquals(1, result.getApiCallCount());
        verify(usageAggregateRepository).save(any());
    }
}
