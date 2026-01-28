package ApiPlatform.service.apikey;
import ApiPlatform.domain.apikey.ApiKey;
import ApiPlatform.domain.organization.Organization;
import ApiPlatform.dto.apikey.ApiKeyResponse;
import ApiPlatform.repository.ApiKeyRepository;
import ApiPlatform.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final OrganizationRepository organizationRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository, OrganizationRepository organizationRepository) {
        this.apiKeyRepository = apiKeyRepository;
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public ApiKeyResponse createKey() {

        Organization org = organizationRepository.findAll().stream().findFirst()
                .orElseThrow(); // placeholder

        String rawKey = UUID.randomUUID().toString().replace("-", "");
        String hashed = hash(rawKey);

        ApiKey apiKey = new ApiKey(org, hashed);
        apiKeyRepository.save(apiKey);

        return new ApiKeyResponse(
                apiKey.getId(),
                rawKey,
                apiKey.isActive(),
                apiKey.getCreatedAt(),
                apiKey.getRevokedAt()
        );
    }

    public List<ApiKeyResponse> listKeys() {

        Organization org = organizationRepository.findAll().stream().findFirst()
                .orElseThrow();

        return apiKeyRepository.findByOrganization(org)
                .stream()
                .map(k -> new ApiKeyResponse(
                        k.getId(),
                        null, // never expose again
                        k.isActive(),
                        k.getCreatedAt(),
                        k.getRevokedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void revokeKey(Long id) {

        ApiKey key = apiKeyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Key not found"));

        key.revoke();
    }

    private String hash(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encoded = digest.digest(value.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : encoded) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing failed");
        }
    }
}

