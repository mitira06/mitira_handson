package com.example.ems.service;

import com.example.ems.auditlog.AuditLog;
import com.example.ems.auditlog.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Exercise 9: Writes to the SECONDARY data source. Note the explicit
 * transactionManager qualifier - without it, @Transactional would default to
 * the @Primary transaction manager (the primary EMS database) and fail,
 * since AuditLog only exists in the secondary persistence unit.
 */
@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Transactional(transactionManager = "secondaryTransactionManager")
    public void log(String action, String entityName, Long entityId) {
        AuditLog entry = AuditLog.builder()
                .action(action)
                .entityName(entityName)
                .entityId(entityId)
                .timestamp(LocalDateTime.now())
                .build();
        auditLogRepository.save(entry);
    }
}
