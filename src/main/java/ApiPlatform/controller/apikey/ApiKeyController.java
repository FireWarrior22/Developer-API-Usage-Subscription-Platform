package ApiPlatform.controller.apikey;
import ApiPlatform.dto.apikey.ApiKeyResponse;
import ApiPlatform.service.apikey.ApiKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/api-keys")
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostMapping
    public ResponseEntity<ApiKeyResponse> createKey() {
        return ResponseEntity.ok(apiKeyService.createKey());
    }

    @GetMapping
    public ResponseEntity<List<ApiKeyResponse>> listKeys() {
        return ResponseEntity.ok(apiKeyService.listKeys());
    }

    @PostMapping("/{id}/revoke")
    public ResponseEntity<Void> revokeKey(@PathVariable Long id) {
        apiKeyService.revokeKey(id);
        return ResponseEntity.ok().build();
    }
}
