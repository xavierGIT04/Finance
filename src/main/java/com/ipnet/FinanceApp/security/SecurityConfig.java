package com.ipnet.FinanceApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ipnet.FinanceApp.security.jwt.AuthEntryPointJwt;
import com.ipnet.FinanceApp.security.jwt.AuthTokenFilter;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserService userService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final AuthTokenFilter authenticationFilter;

    public SecurityConfig(UserService userService, AuthEntryPointJwt unauthorizedHandler, AuthTokenFilter authenticationFilter) {
        this.userService = userService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> 
                    exception.authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(session -> 
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                     
                        .requestMatchers(
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                        
                       
                        .requestMatchers("/api/v1/login").permitAll()
                        
                        
                        .requestMatchers("/", "/error", "/favicon.ico").permitAll()
                        
                       
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                        
                      
                        .requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN", "USER")
                        
                        
                        .requestMatchers(
                                "/client/**",
                                "/compte/**",
                                "/depot/**",
                                "/retrait/**",
                                "/virement/**",
                                "/type-compte/**"
                        ).authenticated()
                        .anyRequest().authenticated()
                );

        // Ajouter le filtre JWT AVANT le filtre d'authentification par défaut
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        // Configurer l'authentification
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	 
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userService);
       
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}