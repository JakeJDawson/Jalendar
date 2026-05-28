package com.jalendar.jalendar_service;

// Import statements.
import org.springframework.context.annotation.*;
import org.springframework.security.config
    .annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.web.authentication
    .UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import java.util.List;

@Configuration
public class SecurityConfig {
    // Used to make Spring automatically give the class a JwtFilter.
    private final JwtAuthenticationFilter jwtFilter;
    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    
    // Decide how Spring Security handles each HTTP request.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) 
        throws Exception {
            http
                // I use JWT, not cookies, I don't need CSRF so I disable it.
                .csrf(csrf -> csrf.disable())
                // Enable CORS which allows React to communicate with Spring.
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                    // The login and register pages are public.
                    .requestMatchers("/api/users/login", "/api/users/register")
                    .permitAll()
                    // Everything else requires authentication.
                    .anyRequest().authenticated())
                // Adds the JWT filter into Spring's security.
                .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class
                );

            // Activate the security config.
            return http.build();
    }

    // Defines what frontend locations are allowed to call the backend.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Allows React to access the backend.
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        // Just allows the HTTP methods.
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE",
            "OPTIONS"));
        // Accept all headers.
        config.setAllowedHeaders(List.of("*"));

        // Apply CORS rules to all endpoints.
        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
