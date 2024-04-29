package org.orion.assistant.config.web;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.orion.assistant.integration.jwt.JwtAuthFilter;
import org.orion.assistant.persistence.service.authservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class. This class is in charge of setting up the security configuration for the application that will require the user to be authenticated to access the endpoints.
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //TODO: pasarlo a constructor
    @Autowired
    private JwtAuthFilter tokenProvider;
    @Autowired
    private UserService userService;
    //oldtd question for myself: dafuk m8 is this?, why are we still here? just to suffer? T-T
    //! From past self: Future self I just want to say that Im so sorry for this, my eyes were bleeding, i just needed it to stop, to let them rest a little 
    //! From past self: I hope you can forgive me for this, I know you will understand
    //! From past self: Time of death: 2024-04-23 17:00:00
    //? From present self: Hi me from the past, i hate you, i hate you so much, i will never forgive you for
    //! Es probable que esto reviente pero lo sabremos en el futuro #disfruta yo del futuro
    //? From present self: Its done, its over, i can finally rest, i can finally sleep, i can finally be free
    /**
     * Security filter chain configuration. This method is the one in charge of setting up the security configuration for the application that 
     * will require the user to be authenticated to access the endpoints.
     * @param http - HttpSecurity object
     * @return SecurityFilterChain object
     * @throws Exception - Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**")
                        .permitAll().anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                    tokenProvider, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Password encoder configuration, using BCryptPasswordEncoder
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication provider configuration
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    /**
     * Authentication manager configuration
     * @param config - AuthenticationConfiguration object
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}

