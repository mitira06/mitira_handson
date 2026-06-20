# Employee Management System — Spring Data JPA & Hibernate

A complete Spring Boot project implementing all 10 exercises from
"Spring Data JPA and Hibernate". Build tool: **Maven**, Java: **17**,
Spring Boot: **3.2.5**.

## Running it

```bash
cd EmployeeManagementSystem
mvn spring-boot:run
```

The app starts on `http://localhost:8080`. On first startup it seeds 3
departments and 5 employees so the endpoints below have data to return.

H2 console: `http://localhost:8080/h2-console`
JDBC URL: `jdbc:h2:mem:emsdb` (user `sa`, password `password`)

> Note: this was generated in an offline sandbox, so it has **not** been
> compiled here (no access to Maven Central). Run `mvn clean install` after
> downloading — if anything doesn't compile, it's most likely a Lombok
> annotation-processing setting in your IDE; enable annotation processing.

## Exercise → code map

| # | Exercise | Where it lives |
|---|----------|-----------------|
| 1 | Project setup, H2 config | `pom.xml`, `application.properties` |
| 2 | JPA entities & relationship | `model/Employee.java`, `model/Department.java` (`@OneToMany`/`@ManyToOne`) |
| 3 | Repositories | `repository/EmployeeRepository.java`, `repository/DepartmentRepository.java` |
| 4 | CRUD + REST controllers | `service/*Service.java`, `controller/*Controller.java` |
| 5 | Query methods, `@Query`, named queries | `EmployeeRepository` (derived methods, `@Query`, `findByEmailDomain` ↔ `@NamedQuery` on `Employee`) |
| 6 | Pagination & sorting | `EmployeeController#search`, `EmployeeService#searchEmployees` |
| 7 | Entity auditing | `model/Auditable.java`, `config/AuditorAwareImpl.java`, `@EnableJpaAuditing` |
| 8 | Projections | `projection/EmployeeNameOnly.java` (interface), `projection/EmployeeSummary.java` (class/constructor expression) |
| 9 | Multiple data sources | `config/PrimaryDataSourceConfig.java` (emsdb), `config/SecondaryDataSourceConfig.java` (auditdb), `auditlog/AuditLog.java` |
| 10 | Hibernate-specific features & batch processing | `@DynamicInsert`/`@DynamicUpdate`/`@BatchSize`/`@Fetch` on entities, `hibernate.jdbc.batch_size` in properties, `EmployeeService#bulkInsertEmployees` |

## Try it out

```bash
# Exercise 4: CRUD
curl http://localhost:8080/api/departments
curl http://localhost:8080/api/employees
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"Fay Adams","email":"fay.adams@acme.com","departmentId":1}'

# Exercise 5: custom query methods
curl "http://localhost:8080/api/employees/by-department-name?name=Engineering"
curl "http://localhost:8080/api/employees/by-email-domain?domain=acme.com"

# Exercise 6: pagination + sorting
curl "http://localhost:8080/api/employees/search?departmentName=Sales&page=0&size=5&sortBy=name&direction=ASC"

# Exercise 8: projections
curl "http://localhost:8080/api/employees/projections/name-only?departmentId=1"
curl "http://localhost:8080/api/employees/projections/summary"

# Exercise 9: audit trail (secondary "auditdb" data source) is written to
# automatically on every create/update/delete — see AuditLogService.

# Exercise 10: batch insert
curl -X POST http://localhost:8080/api/employees/bulk \
  -H "Content-Type: application/json" \
  -d '[{"name":"G1","email":"g1@acme.com","departmentId":1},{"name":"G2","email":"g2@acme.com","departmentId":1}]'
```

Watch the console logs while calling `/bulk` — with `show-sql=true` and
`hibernate.jdbc.batch_size=20` you'll see the inserts grouped into JDBC
batches rather than one round trip per row.
