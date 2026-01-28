package ApiPlatform;

import ApiPlatform.domain.user.User;
import ApiPlatform.dto.auth.AuthResponse;
import ApiPlatform.dto.auth.LoginRequest;
import ApiPlatform.dto.auth.SignupRequest;
import ApiPlatform.repository.UserRepository;
import ApiPlatform.security.JwtTokenProvider;
import ApiPlatform.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthService authService;

    @Test
    void signup_createsUser_andReturnsJwt() {

        SignupRequest request = new SignupRequest(
                "John", "Doe", "john@test.com", "password123"
        );

        when(userRepository.existsByEmail(request.getEmail()))
                .thenReturn(false);

        when(passwordEncoder.encode(any()))
                .thenReturn("encoded-password");

        when(jwtTokenProvider.generateToken(request.getEmail()))
                .thenReturn("jwt-token");

        AuthResponse response = authService.signup(request);

        assertNotNull(response);
        assertEquals("jwt-token", response.getAccessToken());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void login_fails_whenPasswordInvalid() {

        User user = new User(
                "John", "Doe", "john@test.com", "encoded"
        );

        when(userRepository.findByEmail("john@test.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(any(), any()))
                .thenReturn(false);

        LoginRequest request =
                new LoginRequest("john@test.com", "wrong");

        assertThrows(RuntimeException.class,
                () -> authService.login(request));
    }
}
