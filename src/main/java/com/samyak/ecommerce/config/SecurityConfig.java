package com.samyak.ecommerce.config;

import com.samyak.ecommerce.security.CustomUserDetailsService;
import com.samyak.ecommerce.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService
            customUserDetailsService;

    private final BCryptPasswordEncoder
            passwordEncoder;

    private final JwtAuthenticationFilter
            jwtAuthenticationFilter;
    public SecurityConfig(
            CustomUserDetailsService customUserDetailsService,
            BCryptPasswordEncoder passwordEncoder, JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.customUserDetailsService =
                customUserDetailsService;

        this.passwordEncoder =
                passwordEncoder;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(
                        customUserDetailsService);

        provider.setPasswordEncoder(
                passwordEncoder);

        return provider;
    }
    @Bean
    public AuthenticationManager
    authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .authenticationProvider(authenticationProvider())

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(
                                        "/login",
                                        "/register",
                                        "/perform-login",
                                        "/auth/**",
                                        "/css/**",
                                        "/js/**",
                                        "/images/**"
                                )
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform-login")
                        .defaultSuccessUrl("/products-ui", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }
}