package br.com.erudio.config;

import br.com.erudio.security.jwt.JwtTokenProvider;
import jakarta.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfiguration {
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
	Map<String, PasswordEncoder> encoders = new HashMap<>();
	encoders.put("pbkdf2", new Pbkdf2PasswordEncoder("53cr37", 16, 3,Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256));
	DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
	passwordEncoder.setDefaultPasswordEncoderForMatches(passwordEncoder);
	return passwordEncoder;
    }

    
}
