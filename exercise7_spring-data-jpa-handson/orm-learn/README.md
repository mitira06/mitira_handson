# orm-learn — Spring Data JPA Hands-on Project

One continuous project covering all hands-on exercises across the training docs, built incrementally.

## Progress

- ✅ **Doc 1** — Project setup, `Country` entity/repository/service, basic `findAll()` test (Hands-on 1), plus full CRUD (Hands-on 6-9: find by code w/ `CountryNotFoundException`, add, update, delete)
- ✅ **Doc 2** — Query Methods + relationship mapping:
  - ✅ Hands-on 1 (Query Methods on `country`: contains, contains+sorted, starts-with)
  - ⏸️ Hands-on 2 (stock table) — **still needs `stock-data.csv`**, not available; skipped
  - ✅ Hands-on 3 (`Employee`, `Department`, `Skill` entities + repositories) — **uses generated sample data**, see note below
  - ✅ Hands-on 4 (`@ManyToOne` Employee → Department)
  - ✅ Hands-on 5 (`@OneToMany` Department → Employees)
  - ✅ Hands-on 6 (`@ManyToMany` Employee ↔ Skill)
- 🔄 **Doc 3** — HQL/JPQL, fetch optimization, Criteria Query:
  - ✅ Hands-on 1 (conceptual, no code)
  - ✅ Hands-on 2 (HQL with `fetch join`, `getAllPermanentEmployees()`)
  - ⏸️ Hands-on 3 (quiz attempt schema) — **needs `quiz.mwb`**, not available; skipped
  - ✅ Hands-on 4 (HQL `AVG()` aggregate, with/without department filter)
  - ✅ Hands-on 5 (Native Query example)
  - ⏸️ Hands-on 6 (Criteria Query) — conceptual/reading hands-on, no fixed code deliverable in the doc; ask if you want a worked example added

## ⚠️ Important note on Hands-on 3 (Doc 2) and the `payroll.sql` gap

The doc says to populate `Department`/`Employee`/`Skill` tables by running a `payroll.sql` script
from a `spring-data-jpa-files` folder on your course platform. **That file was never provided to
you and isn't available anywhere**, so I generated realistic sample data myself, matching the
**exact column names and table structure** the docs describe (`em_id`, `em_name`, `em_salary`,
`em_permanent`, `em_dp_id`, `dp_id`, `dp_name`, `sk_id`, `sk_name`, `es_em_id`, `es_sk_id`).

This means: the **code, annotations, queries, and mechanisms are all correct** and demonstrate
exactly what each hands-on asks for. The **specific employee names/salaries** will differ from
whatever the original `payroll.sql` contained (since we never had it) — but the JPA/Hibernate
concepts, query results, and log output structure will match what's expected.

Seed data (`src/main/resources/data.sql`, appended after the country list):
- 3 Departments: Engineering (id 1), Human Resources (id 2), Finance (id 3)
- 6 Skills: Java, SQL, Spring Boot, Communication, Excel, Project Management
- 6 Employees spread across the 3 departments (department 3 has 3 employees, to satisfy
  Hands-on 5's requirement of "a department with more than one employee")
- A handful of employee_skill relationships, leaving employee id 3 with **no skills initially**
  (used to demonstrate Hands-on 6's "add skill to employee" test)

## What was added in this round (Doc 1 Hands-on 6-9: Country CRUD)

`CountryNotFoundException` (new `service/exception` package), plus `findCountryByCode()`,
`addCountry()`, `updateCountry()`, `deleteCountry()` in `CountryService`. Test methods
`testFindCountryByCode()`, `testAddCountry()`, `testUpdateCountry()`, `testDeleteCountry()`
were added to `OrmLearnApplication` and run right after the Doc 2 Hands-on 1 query method
tests. They add/update/delete a throwaway country with code `ZZ` so the real country data
isn't disturbed, and the delete test confirms `CountryNotFoundException` is thrown afterward.

## What was added in this round (Doc 2 Hands-on 3–6, Doc 3 Hands-on 2/4/5)

**New entities** (`src/main/java/.../model/`): `Department.java`, `Skill.java`, `Employee.java`
**New repositories**: `DepartmentRepository`, `SkillRepository`, `EmployeeRepository` (the last one
includes the Doc 3 HQL/native query methods)
**New services**: `DepartmentService`, `SkillService`, `EmployeeService`
**OrmLearnApplication.java**: rewritten with all test methods active in sequence (rather than
commented in/out like the doc suggests, since we want one full run to verify everything at once)

Test methods added, in the order they run:
1. `testGetEmployee()` — fetch employee 1 with department + skills (EAGER fetch)
2. `testAddEmployee()` — create + save a new employee in department 1
3. `testUpdateEmployee()` — move employee 1 to department 2
4. `testGetDepartment()` — fetch department 3 with its employee list (`@OneToMany`)
5. `testAddSkillToEmployee()` — add a skill to employee 3 (`@ManyToMany`)
6. `testGetAllPermanentEmployees()` — HQL with `fetch join` (Doc 3 Hands-on 2)
7. `testGetAverageSalary()` / `testGetAverageSalaryByDepartment()` — HQL `AVG()` (Doc 3 Hands-on 4)
8. `testGetAllEmployeesNative()` — native SQL query (Doc 3 Hands-on 5)

## Still pending / blocked

- **Doc 2, Hands-on 2** (stock Query Methods) — needs `stock-data.csv`
- **Doc 3, Hands-on 3** (quiz attempt HQL) — needs `quiz.mwb` (MySQL Workbench schema file)
- **Doc 3, Hands-on 6** (Criteria Query) — the doc only points to an external tutorial link with
  no concrete code spec to implement; let me know if you'd like a worked example built anyway

## How to run

You need: **Java 11+** and **Maven 3.6+** (and internet access, so Maven can download dependencies the first time).

```bash
cd orm-learn
mvn spring-boot:run
```

Or build a jar and run it:
```bash
mvn clean package
java -jar target/orm-learn-0.0.1-SNAPSHOT.jar
```

On startup you should see Hibernate SQL logs (table creation + insert), then log lines from
`OrmLearnApplication`:
```
... Inside main
... Start
... countries=[Country [code=IN, name=India], Country [code=US, name=United States of America]]
... End
```

## What's different from the doc, and why

| Doc says | This project uses | Why |
|---|---|---|
| MySQL Server 8.0 | **PostgreSQL** | You have PostgreSQL installed and running locally, and your trainer wants screenshots taken in PostgreSQL. All the JPA/Hibernate code is identical either way — only the driver/dialect/URL change. |
| Manually run `mysql -u root -p` + `create schema` | `CREATE DATABASE ormlearn;` in psql/pgAdmin | Same idea, PostgreSQL syntax. See `postgresql-handson-guide.sql`. |
| `spring.jpa.hibernate.ddl-auto=validate` | `update` | `validate` requires the tables to already exist. `update` lets Hibernate create them automatically on first run, and — unlike `create-drop` — does **not** drop them on shutdown, so the tables/data are still there in pgAdmin/psql afterwards for screenshots. |
| Manually run `insert into country values (...)` | `src/main/resources/data.sql` | Spring Boot auto-runs this file on startup — full country list, plus department/skill/employee/employee_skill seed data. Runs once cleanly; re-running on a second startup will throw duplicate-key errors (the app still starts, just ignore them, or `TRUNCATE` the tables first). |
| `co_code` / `co_name` column names in the raw SQL section | `code` / `name` | The doc's own `Country.java` example maps `@Column(name="code")` / `@Column(name="name")`, not `co_code`/`co_name` — that mismatch is in the original doc. The Java class is what Hibernate actually uses, so that's what's kept consistent everywhere, including in `postgresql-handson-guide.sql`. |

## Taking the hands-on screenshots in PostgreSQL

See **`postgresql-handson-guide.sql`** — it has the PostgreSQL equivalent of every manual SQL step
and every Query Method / HQL / native query result across Docs 1-3, in order, each followed by a
`-- SCREENSHOT:` comment telling you what to capture. Run it section by section in psql or
pgAdmin's Query Tool. You can also just `mvn spring-boot:run` first (Hibernate + `data.sql` will
create and seed everything automatically), then run only the `SELECT` statements from the guide
to produce the same screenshots without typing the `CREATE`/`INSERT` statements by hand.

## Switching to MySQL later

1. Make sure MySQL is running and you've created the `ormlearn` schema (see the doc's instructions).
2. In `application.properties`, comment out the PostgreSQL block and uncomment the MySQL block.
3. Add the MySQL connector dependency back in `pom.xml` (commented block) and change `username`/`password` to match your local MySQL.
4. Since `ddl-auto=validate` is used for MySQL in the doc, you'd need to create the `country` table yourself first — Hibernate will only check it matches, not create it.

## ⚠️ Note on your Maven setup

Your `mvn` is configured to use a corporate proxy (`proxy.cognizant.com`) for downloading
dependencies. If you're not on the Cognizant VPN/network, that proxy host won't resolve and
**any new dependency** will fail to download (not just MySQL). For that reason the MySQL
connector dependency has been removed from `pom.xml` for now (commented out) — H2 alone is
enough to run everything in this project. Add the MySQL dependency back (see the commented
block in `pom.xml`) once you're on the corporate network and actually want to connect to MySQL.

## ⚠️ Not yet tested by me

I don't have Maven/internet access in this environment, so I wrote this code carefully against
standard Spring Boot/JPA conventions but **could not actually compile or run it myself**. Please run
it on your machine and send me any errors — I'll fix them immediately.

## Project structure

```
src/main/java/com/cognizant/ormlearn/
├── OrmLearnApplication.java      # main() + test method
├── model/Country.java            # @Entity
├── repository/CountryRepository.java   # JpaRepository<Country, String>
└── service/CountryService.java   # @Service, getAllCountries()
src/main/resources/
├── application.properties        # H2 config + logging
└── data.sql                      # seed data (IN, US)
```
