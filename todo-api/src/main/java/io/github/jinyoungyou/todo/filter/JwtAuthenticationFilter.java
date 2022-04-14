package io.github.jinyoungyou.todo.filter;

import io.github.jinyoungyou.todo.jwt.JwtManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private final JwtManager jwtManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtManager jwtManager) {
        super(authenticationManager);
        this.jwtManager = jwtManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            getAuthentication(request).ifPresent(auth -> {
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(auth);
            });
            super.doFilterInternal(request, response, chain);
        }
        catch (RuntimeException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private Optional<Authentication> getAuthentication(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (jwt == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(jwtManager.getAuthentication(jwt.substring("Bearer ".length())));
    }
}
