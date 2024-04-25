package org.orion.assistant.config.web;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.orion.assistant.integration.jwt.JwtAuthenticationFilter;
import org.orion.assistant.integration.jwt.JwtTokenProvider;
import org.orion.assistant.persistence.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //TODO: pasarlo a constructor
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    //TODO question for myself: dafuk m8 is this?, why are we still here? just to suffer? T-T
    //! From past self: Future self I just want to say that Im so sorry for this, my eyes were bleeding, i just needed it to stop, to let them rest a little 
    //! From past self: I hope you can forgive me for this, I know you will understand
    //! From past self: Time of death: 2024-04-23 17:00:00
    //? From present self: Hi me from the past, i hate you, i hate you so much, i will never forgive you for
    //! Es probable que esto reviente pero lo sabremos en el futuro #disfruta yo del futuro
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(
                new JwtAuthenticationFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //TODO terminar de implementar configuracion de autentificacion, asegurar correcto funcionamiento del bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth, UserDTO userDTO) throws Exception {
        // Here you configure your authentication provider, for example, in-memory, JDBC, etc.
        auth.inMemoryAuthentication()
            .withUser(userDTO.getName()).password(passwordEncoder().encode(userDTO.getPassword())).roles(userDTO.getRole());
        return auth.build();
    }
}

