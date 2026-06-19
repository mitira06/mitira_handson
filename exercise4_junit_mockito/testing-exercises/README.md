# Testing Exercises — JUnit, Mockito, Spring Test, SLF4J

A single Maven project containing working solutions for all 7 exercise sheets:
1. JUnit Basic Testing
2. JUnit Advanced Testing
3. Mockito Basic
4. Mockito Advanced
5. Mockito + Spring (mocking dependencies)
6. JUnit + Spring Test (full Spring Testing exercises)
7. SLF4J Logging

## Requirements
- Java 17 (you said this is installed)
- Maven (you said this is installed)
- Internet access on first build (Maven needs to download dependencies once; they're cached afterward)

## How to build and run all tests

Unzip the project, then from the project root (the folder containing `pom.xml`):

```bash
cd testing-exercises
mvn clean test
```

This compiles everything and runs all test classes. You should see a `BUILD SUCCESS` with a test summary (around 35+ tests).

## Run a single test class

```bash
mvn -Dtest=UserServiceTest test
```

## Run the logging examples (SLF4J sheet)

These have `main` methods, so run them directly:

```bash
mvn compile exec:java -Dexec.mainClass="com.example.app.logging.LoggingExample"
mvn compile exec:java -Dexec.mainClass="com.example.app.logging.ParameterizedLoggingExample"
mvn compile exec:java -Dexec.mainClass="com.example.app.logging.AppenderLoggingExample"
```

(If `exec:java` complains about a missing plugin, just run them from your IDE instead — right-click the file → Run.)

After running `AppenderLoggingExample`, check the project root for a generated `app.log` file (file appender output).

## Run the Spring Boot app itself

```bash
mvn spring-boot:run
```

Then try:
- `POST http://localhost:8080/users` with body `{"name":"Alice"}`
- `GET http://localhost:8080/users/1`

## Project layout

```
src/main/java/com/example/app/
  basics/        -> Calculator, EvenChecker, ExceptionThrower, PerformanceTester (plain JUnit targets)
  calculator/    -> CalculatorService (Spring-managed bean)
  logging/       -> SLF4J examples (sheet 6)
  mockito/
    basic/       -> ExternalApi/MyService/Notifier/AlertService (Mockito Basic sheet)
    repo/        -> Repository/Service (Mockito Advanced ex1, ex5)
    api/         -> RestClient/ApiService (Mockito Advanced ex2)
    fileio/      -> FileReader/FileWriter/FileService (Mockito Advanced ex3)
    network/     -> NetworkClient/NetworkService (Mockito Advanced ex4)
  user/          -> User entity, UserRepository, UserService, UserController,
                     GlobalExceptionHandler (covers both Spring-related sheets)

src/test/java/com/example/app/...   -> mirrors the above, one test class per exercise

src/main/resources/
  application.properties  -> H2 datasource config for the running app
  logback.xml              -> console + file appenders (SLF4J sheet ex3)

src/test/resources/
  application.properties  -> separate H2 instance for tests
```

## Mapping exercises to test files

| Sheet | Exercise | Test file |
|---|---|---|
| JUnit Basic | 2. Basic tests | `basics/CalculatorBasicTest.java` |
| JUnit Basic | 3. Assertions | `basics/AssertionsTest.java` |
| JUnit Basic | 4. AAA + setup/teardown | `basics/CalculatorAaaTest.java` |
| JUnit Advanced | 1. Parameterized tests | `basics/EvenCheckerTest.java` |
| JUnit Advanced | 2. Test suites | `basics/AllTests.java` |
| JUnit Advanced | 3. Execution order | `basics/OrderedTests.java` |
| JUnit Advanced | 4. Exception testing | `basics/ExceptionThrowerTest.java` |
| JUnit Advanced | 5. Timeout testing | `basics/PerformanceTesterTest.java` |
| Mockito Basic | 1–7 | `mockito/basic/MyServiceTest.java` (one method per exercise) |
| Mockito Advanced | 1. Repository | `mockito/repo/ServiceTest.java` |
| Mockito Advanced | 2. REST client | `mockito/api/ApiServiceTest.java` |
| Mockito Advanced | 3. File I/O | `mockito/fileio/FileServiceTest.java` |
| Mockito Advanced | 4. Network | `mockito/network/NetworkServiceTest.java` |
| Mockito Advanced | 5. Multiple returns | `mockito/repo/MultiReturnServiceTest.java` |
| Mockito+Spring | 1. Controller mock | `user/UserControllerTest.java` |
| Mockito+Spring | 2. Repository mock | `user/UserServiceTest.java` |
| Mockito+Spring | 3. Integration + mocked service | `user/UserServiceMockedIntegrationTest.java` |
| Spring Testing | 1. Calculator unit test | `calculator/CalculatorServiceTest.java` |
| Spring Testing | 2. Repository mock | `user/UserServiceTest.java` |
| Spring Testing | 3. MockMvc controller | `user/UserControllerTest.java` |
| Spring Testing | 4. Full integration | `user/UserIntegrationTest.java` |
| Spring Testing | 5. POST endpoint | `user/UserControllerPostTest.java` |
| Spring Testing | 6. Exception handling (service) | `user/UserServiceTest.java` |
| Spring Testing | 7. Custom repository query | `user/UserRepositoryTest.java` |
| Spring Testing | 8. Controller exception handling | `user/UserControllerExceptionHandlingTest.java` |
| Spring Testing | 9. Parameterized test | `calculator/CalculatorParameterizedTest.java` |
| SLF4J | 1–3 | `logging/*.java` (run as Java apps, not tests) |

## Notes
- JUnit 5 (Jupiter) is used throughout, even where the original sheet showed JUnit 4 syntax (`@Before`/`@After`) — JUnit 5 equivalents (`@BeforeEach`/`@AfterEach`) are used since that's what `spring-boot-starter-test` provides by default.
- All Mockito code uses `org.mockito.Mockito` static imports, matching the originals.
- I could not run `mvn test` in this sandbox (no internet/Maven here), so verify the build on your machine. If something doesn't compile, paste me the error and I'll fix it.
