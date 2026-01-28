package ApiPlatform.dto.apikey;
import java.time.LocalDateTime;

public class ApiKeyResponse {

    private Long id;

    // Shown ONLY at creation / rotation time
    private String apiKey;

    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime revokedAt;

    public ApiKeyResponse(Long id, String apiKey, boolean active, LocalDateTime createdAt, LocalDateTime revokedAt) {
        this.id = id;
        this.apiKey = apiKey;
        this.active = active;
        this.createdAt = createdAt;
        this.revokedAt = revokedAt;
    }

    public Long getId() {
        return id;
    }

    public String getApiKey() {
        return apiKey;
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
}
