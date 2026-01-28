package ApiPlatform;
import ApiPlatform.domain.apikey.ApiKey;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.dto.apikey.ApiKeyResponse;
import ApiPlatform.repository.ApiKeyRepository;
import ApiPlatform.repository.OrganizationRepository;
import ApiPlatform.service.apikey.ApiKeyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiKeyServiceTest {

    @Mock
    private ApiKeyRepository apiKeyRepository;

    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private ApiKeyService apiKeyService;

    @Test
    void createKey_generatesAndStoresHashedKey() {

        Organization org = new Organization("TestOrg");

        when(organizationRepository.findAll())
                .thenReturn(List.of(org));

        ApiKeyResponse response = apiKeyService.createKey();

        assertNotNull(response.getApiKey());
        assertTrue(response.isActive());

        verify(apiKeyRepository).save(any(ApiKey.class));
    }

    @Test
    void revokeKey_marksKeyInactive() {

        Organization org = new Organization("TestOrg");
        ApiKey key = new ApiKey(org, "hash");

        when(apiKeyRepository.findById(1L))
                .thenReturn(Optional.of(key));

        apiKeyService.revokeKey(1L);

        assertFalse(key.isActive());
        assertNotNull(key.getRevokedAt());
    }
}
