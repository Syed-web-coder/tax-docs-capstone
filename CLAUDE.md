# Tax Docs Capstone — Project Conventions

This file documents the conventions every contributor must follow. These aren't arbitrary rules — each one exists to prevent a specific class of bug or inconsistency that shows up in real financial software. Read it before writing your first line of code.

---

## Java Version

Use **JDK 17 or later**. The project relies on modern language features (records, sealed classes, pattern matching) that don't exist in older versions. Don't target a lower `--release` level just because it compiles — we develop and deploy on 17+.

---

## Money: Always `BigDecimal`, Never `double` or `float`

Any value representing money must be a `BigDecimal` with scale 2 and `RoundingMode.HALF_UP`.

```java
BigDecimal amount = new BigDecimal("19.99");
amount = amount.setScale(2, RoundingMode.HALF_UP);
```

Floating-point types (`double`, `float`) cannot represent most decimal fractions exactly. `0.1 + 0.2` in a `double` is not `0.3` — it's `0.30000000000000004`. That's fine for scientific computation; it's a compliance failure in a tax application. `BigDecimal` is exact, and `HALF_UP` matches the rounding behavior expected in financial and tax contexts.

---

## IDs: Always `String` (UUID or Prefixed)

Every entity ID must be a `String`. Use UUID v4 (`UUID.randomUUID().toString()`) or a human-readable prefixed form like `"doc_a1b2c3..."`.

```java
String documentId = "doc_" + UUID.randomUUID().toString().replace("-", "");
```

`int` and `long` IDs are sequential, which leaks information (how many records exist, creation order) and creates coordination problems across distributed systems or parallel test runs. String IDs are opaque, globally unique, and safe to expose in URLs or API responses without revealing internal state.

---

## Dates and Times: `java.time` Only

- Use `LocalDate` for calendar dates (tax year, filing deadline, date of birth).
- Use `Instant` for timestamps (when a record was created or modified).
- Never use `java.util.Date`, `java.sql.Date`, or `Calendar`.

```java
LocalDate filingDeadline = LocalDate.of(2025, 4, 15);
Instant createdAt = Instant.now();
```

The legacy date/time classes are mutable, have confusing month indexing (January is 0), and conflate "a calendar date" with "a point in time." `java.time` (JSR-310) was designed to replace them. Using the old classes in new code is a source of timezone bugs and library incompatibilities.

---

## Immutability by Default

Classes and fields should be `final` by default. Instance fields should be `private final` and set only through the constructor. Do not use Lombok's `@Data` — it generates mutable setters and a permissive `equals`/`hashCode` that are rarely what you want for domain objects.

```java
public final class TaxDocument {
    private final String id;
    private final String ownerId;
    private final LocalDate taxYear;

    public TaxDocument(String id, String ownerId, LocalDate taxYear) {
        this.id = id;
        this.ownerId = ownerId;
        this.taxYear = taxYear;
    }
}
```

Mutable objects are harder to reason about and easier to corrupt — especially when shared across threads or passed through multiple service layers. Making immutability the default means you only introduce mutability when there's a deliberate reason to, not by accident.

---

## Tests: JUnit 5 Only

All tests use JUnit 5. Use the standard annotations and assertions: `@Test`, `@BeforeEach`, `assertEquals`, `assertTrue`, `assertThrows`. Do not mix in JUnit 4 annotations (`@org.junit.Test`, `@Before`, `@Rule`).

```java
@Test
void shouldRejectNegativeAmount() {
    assertThrows(IllegalArgumentException.class, () -> new TaxDocument(null, "-5.00"));
}
```

Mixing JUnit 4 and JUnit 5 in the same project causes silent test failures — JUnit 4 annotations are silently ignored by the JUnit 5 runner, so tests appear to pass without actually running. Using one framework consistently eliminates that risk.

---

## Package Structure

All production source code lives under the root package `com.uptimecrew.taxdocs`.

```
com.uptimecrew.taxdocs.domain       // core entities and value objects
com.uptimecrew.taxdocs.service      // business logic
com.uptimecrew.taxdocs.repository   // data access
com.uptimecrew.taxdocs.api          // controllers / request handlers
com.uptimecrew.taxdocs.config       // Spring or framework configuration
```

A consistent root package prevents naming collisions, makes it obvious what belongs to this project versus a third-party library, and keeps IDE navigation predictable for everyone on the team.
