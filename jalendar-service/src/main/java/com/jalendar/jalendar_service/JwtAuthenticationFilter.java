package com.jalendar.jalendar_service;

// Import statements.
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication
    .UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import org.springframework.stereotype.Component;

// This is managed by Spring, and only runs once per HTTP request.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // Injects the JWTutil class in order to decode and validate the token.
    private final JwtUtil jwtUtil;
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Primary filter logic.
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        /**
         * Skip filtering public paths, like login and register. This is done
         * elsewhere, so I've commented it out, but I originally wrote the
         * lines for debugging, so I've left them here in case I need them 
         * again.
         */
        /**String path = request.getServletPath();
        if(path.startsWith("/api/users/login") ||
            path.startsWith("/api/users/register")) {
                filterChain.doFilter(request, response);
                return;
        }*/

        // Grabs the JWT from an HTTP request.
        String header = request.getHeader("Authorization");
        // Rejects requests without tokens.
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Removes non-JWT token characters
        String token = header.substring(7);

        try {
            // Try to get the email from the token.
            String email = jwtUtil.extractEmail(token);

            // Basically makes an object to say "This guy is authenticated."
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    Collections.emptyList()
            );
            
            // Attached request details and session info.
            auth.setDetails(new org.springframework.security.web
                .authentication.WebAuthenticationDetailsSource()
                .buildDetails(request));

            // Actually officially decide that the user is authenticated.
            SecurityContextHolder.getContext().setAuthentication(auth);
        // If anything is wrong, the request is rejected.
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Passes the request to the next filter, then the controller.
        filterChain.doFilter(request, response);
    }
}