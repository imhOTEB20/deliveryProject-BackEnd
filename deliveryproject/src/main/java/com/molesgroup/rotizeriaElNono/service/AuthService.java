package com.molesgroup.rotizeriaElNono.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    protected String getAuth0IdFromToken() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject(); // Devuelve el Auth0 ID
        }
        throw new IllegalStateException("No JWT found in security context");
    }

}
