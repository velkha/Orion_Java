package org.orion.assistant.integration.jwt;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.orion.assistant.persistence.service.authservices.JwtService;
import org.orion.assistant.persistence.service.authservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter to authenticate the user with the JWT token
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    //TODO: pasarlo a constructor
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    
    /**
     * Filters the incoming HTTP request and response to perform JWT authentication.
     * If the request contains a valid JWT token in the "Authorization" header, it extracts the user username
     * from the token and checks if the user is authenticated. If the user is authenticated, it sets the
     * authentication context for the request. Otherwise, it allows the request to proceed without authentication.
     *
     * @param request      the incoming HTTP request
     * @param response     the HTTP response
     * @param filterChain the filter chain for processing the request
     * @throws ServletException if an error occurs during the filter process
     * @throws IOException      if an I/O error occurs during the filter process
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Get the "Authorization" header from the request
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String user;

        // If the "Authorization" header is empty or does not start with "Bearer ", allow the request to proceed without authentication
        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token from the "Authorization" header
        jwt = authHeader.substring(7);
        
        // Extract the username from the JWT token
        user = jwtService.extractUserName(jwt);

        // If the username is not empty and the user is not already authenticated, perform authentication
        if (StringUtils.isNotEmpty(user)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load the user details from the user service
            UserDetails userDetails = userService.userDetailsService()
                    .loadUserByUsername(user);
            
            // If the JWT token is valid for the user, set the authentication context for the request
            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        
        // Proceed with the filter chain
        filterChain.doFilter(request, response);
    }
}
