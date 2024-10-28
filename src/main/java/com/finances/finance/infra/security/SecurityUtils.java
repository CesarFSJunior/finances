package com.finances.finance.infra.security;

import com.finances.finance.domain.entities.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    @Bean
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;

        User authenticatedUser = (User)authentication.getPrincipal();

        return authenticatedUser;
    }

}
