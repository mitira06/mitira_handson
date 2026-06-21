package com.example.ems.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Exercise 7: Supplies the value used for @CreatedBy / @LastModifiedBy.
 * This project has no authentication layer, so a fixed "system" auditor is
 * returned. In a real application this would read the current principal
 * from Spring Security's SecurityContextHolder, e.g.:
 *
 *   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 *   return Optional.ofNullable(authentication).map(Authentication::getName);
 */
@Component("auditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("system");
    }
}
