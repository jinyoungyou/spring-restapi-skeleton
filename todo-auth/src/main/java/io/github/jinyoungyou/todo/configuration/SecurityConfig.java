package io.github.jinyoungyou.todo.configuration;

import io.github.jinyoungyou.todo.exception.handler.GlobalExceptionHandler;
import io.github.jinyoungyou.todo.jwt.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.secret}")
    private String secret;

    private final GlobalExceptionHandler globalExceptionHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        Filter filter = new JwtAuthenticationFilter(
//                authenticationManager(), jwtManager());

        http
                .cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions().disable()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .anonymous()
//
//                .and()
//                .authorizeRequests()
//                .antMatchers("/auth/**", "/api/**").permitAll()
//                .anyRequest().authenticated()
//                // Spring Security JWT 설정
//
//                .and()
//                // 세션 사용 안함 (JWT 씀)
//                .addFilter(filter)
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // bcrypt strength = 4
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public JwtManager jwtManager() {
        // bcrypt strength = 4
        return new JwtManager(secret);
    }
}
