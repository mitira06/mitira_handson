package com.example.ems.auditlog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Exercise 9: Lives entirely in the SECONDARY data source ("auditdb"), separate
 * from the primary EMS database, to demonstrate managing multiple data sources
 * in one Spring Boot application.
 */
@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;       // e.g. CREATE, UPDATE, DELETE
    private String entityName;   // e.g. "Employee", "Department"
    private Long entityId;
    private LocalDateTime timestamp;
}
