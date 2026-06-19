# Spring Core + Maven -- All Exercises

This project contains two Maven modules, both built from the same growing
codebase, covering all 9 exercises from the CTS Digital Nuture assignment.

```
SpringCoreMaven/
├── LibraryManagement/        <- Exercises 1, 2, 3, 4, 5, 6, 7, 8 (classic Spring)
└── LibraryManagementBoot/    <- Exercise 9 (Spring Boot)
```

Why two modules instead of one? Classic Spring (XML config, manual beans) and
Spring Boot (auto-configuration) use different dependency sets that conflict
if mixed in a single pom.xml. Keeping them as sibling folders under one
project root is the standard, recommended approach -- it's still "one
project" you open as a whole in VS Code, but each module runs independently.

---

## Exercise checklist

| # | Exercise | Where | Status |
|---|----------|-------|--------|
| 1 | Basic Spring Application | LibraryManagement | Done |
| 2 | Dependency Injection (setter) | LibraryManagement | Done |
| 3 | Logging with Spring AOP (@Around) | LibraryManagement | Done |
| 4 | Maven project + dependencies + compiler plugin | LibraryManagement/pom.xml | Done |
| 5 | Spring IoC Container configuration | LibraryManagement | Done |
| 6 | Annotation-based bean configuration (@Service/@Repository + component-scan) | LibraryManagement | Done |
| 7 | Constructor + Setter Injection | LibraryManagement/BookService.java | Done |
| 8 | Basic AOP (@Before, @After, @Around advice) | LibraryManagement/LoggingAspect.java | Done |
| 9 | Spring Boot app with REST + JPA + H2 | LibraryManagementBoot | Done |

---

## Module 1: LibraryManagement (Exercises 1-8)

### Structure
```
LibraryManagement/
├── pom.xml                                          (Ex 1, 4: deps + compiler plugin)
└── src/main/
    ├── java/com/library/
    │   ├── LibraryManagementApplication.java         (Ex 1: loads context, runs demo)
    │   ├── service/BookService.java                  (Ex 2, 6, 7: DI + annotations)
    │   ├── repository/BookRepository.java             (Ex 1, 6: @Repository)
    │   └── aspect/LoggingAspect.java                  (Ex 3, 8: @Before/@After/@Around)
    └── resources/
        └── applicationContext.xml                     (Ex 1, 2, 3, 5, 6, 7: bean config)
```

### How to run
From inside `LibraryManagement/`:
```bash
mvn compile exec:java
```
Or in VS Code: open `LibraryManagementApplication.java` and click **Run** above `main`.

### What you'll see in the console
```
Spring context loaded successfully!
BookService bean: com.library.service.BookService@<hash>
[BEFORE] About to call: void com.library.service.BookService.addBook(String)
[LOG] void com.library.service.BookService.addBook(String) executed in 0ms
Book added to repository: Effective Java
[AFTER] Finished call: void com.library.service.BookService.addBook(String)
...
Books in repository: [Effective Java, Clean Code]
```

The `[BEFORE]`, `[LOG]`, and `[AFTER]` lines come from the three advice types
in `LoggingAspect` (Exercises 3 and 8), automatically wrapping every
`BookService` method call -- no changes needed to `BookService` itself, which
is the entire point of AOP.

### Notes on how the exercises connect

- **applicationContext.xml** evolved across exercises: it started with manual
  `<bean>` tags (Ex 1, 2, 5), gained AOP support (Ex 3), then switched to
  `<context:component-scan>` (Ex 6). The original manual `<bean>` declarations
  and the XML constructor/setter-injection examples (Ex 7) are kept as
  commented-out blocks in the file so you can see both configuration styles
  side by side -- just don't uncomment them while component-scan is active,
  or you'll get duplicate bean id errors.
- **BookService** has both a constructor and a setter for `BookRepository`
  (Exercise 7's requirement), plus `@Autowired` on the field (Exercise 6).
  Spring resolves these in this order of preference: constructor first if one
  exists, otherwise field/setter injection.
- **LoggingAspect** has three advice methods (`@Before`, `@After`, `@Around`)
  all matching the same pointcut, satisfying Exercise 3 (timing) and Exercise
  8 (before/after logging) simultaneously.

---

## Module 2: LibraryManagementBoot (Exercise 9)

### Structure
```
LibraryManagementBoot/
├── pom.xml                                    (Spring Boot starters: web, JPA, H2)
└── src/main/
    ├── java/com/library/boot/
    │   ├── LibraryManagementBootApplication.java   (entry point)
    │   ├── model/Book.java                          (@Entity)
    │   ├── repository/BookRepository.java            (JpaRepository interface)
    │   └── controller/BookController.java            (REST CRUD endpoints)
    └── resources/
        └── application.properties                    (H2 datasource config)
```

### How to run
From inside `LibraryManagementBoot/`:
```bash
mvn spring-boot:run
```
The app starts an embedded Tomcat server on **http://localhost:8080**.

### Testing the REST endpoints

Using `curl` (or import these into Postman):

```bash
# Create a book
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Effective Java","author":"Joshua Bloch"}'

# Get all books
curl http://localhost:8080/api/books

# Get one book by id
curl http://localhost:8080/api/books/1

# Update a book
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Effective Java (3rd Edition)","author":"Joshua Bloch"}'

# Delete a book
curl -X DELETE http://localhost:8080/api/books/1
```

You can also browse the H2 database directly at
`http://localhost:8080/h2-console` while the app is running
(JDBC URL: `jdbc:h2:mem:librarydb`, username `sa`, blank password).

---

## Opening this in VS Code

1. Unzip the file.
2. `File > Open Folder` and select the `SpringCoreMaven` folder (the parent,
   containing both modules) -- the Java extension will detect both as
   separate Maven projects.
3. Make sure the **Extension Pack for Java** is installed -- it handles
   dependency downloads, run buttons, and debugging automatically.
4. Give it a minute on first open to download dependencies (you'll see
   progress in the bottom status bar).

If a module's dependencies don't resolve automatically, right-click its
`pom.xml` and choose **"Reload Project"** (or run `mvn clean install` in its
folder from the terminal).
