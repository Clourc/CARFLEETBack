package com.project.carfleet.security;

import com.project.carfleet.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configure(http))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(
                                "/users/login"
                                , "/users"
                                , "/users/{id}"
                                , "/users/{cp}"
                                , "/users/{id}/delete"
                                , "/users/register"
                                , "/vehicles"
                                , "/vehicles/{id}"
                                , "/vehicles/{id}/delete"
                                , "/vehicles/add"
                                , "/fleets"
                                , "/fleet/{id}"
                                , "/fleet/{id}/delete"
                                , "/models"
                                , "/models/{id}"
                                , "/models/{id}/delete"
                                , "/reservations"
                                , "/reservations/{id}"
                                , "/reservations/{id}/delete").permitAll()
                        .requestMatchers("/admin-only"
                                    /*, "/users"
                                    , "/users/{id}/delete"
                                    , "/users/register"
                                    , "/vehicles/{id}/delete"
                                    , "/vehicles/{id}/add"
                                    , "/fleets"
                                    , "/fleets/{id}"
                                    , "/fleets/{id}/delete"
                                    , "/models/{id}/delete"
                                    , "/models/add"
                                    , "/reservations/{id}/delete"
                                    , "/reservations/{id}/add"*/)
                        .hasAuthority("ADMIN")
                        .requestMatchers("/all-users"
                                    /*, "/users/{id}"
                                    , "/vehicles"
                                    , "/vehicles/{id}"
                                    , "/fleets/{id}"
                                    , "/models"
                                    , "/models/{id}"
                                    , "/reservations"
                                    , "/reservations/{id}"
                                    , "/reservations/add"*/)
                        .hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("other_route_example/**")
                        .authenticated())
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers("")
                        .disable()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
