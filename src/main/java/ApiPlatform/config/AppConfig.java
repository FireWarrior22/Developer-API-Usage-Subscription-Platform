package ApiPlatform.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    /**
     * Password encoder for user passwords.
     * BCrypt is industry standard.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ApplicationEventPublisher is already provided by Spring,
     * but exposing intent here improves readability and clarity.
     */
    @Bean
    public ApplicationEventPublisher applicationEventPublisherBean(
            ApplicationEventPublisher publisher
    ) {
        return publisher;
    }
}

