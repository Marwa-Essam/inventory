# Inventory QA Automation

## Overview

Inventory QA Automation is an end-to-end quality assurance automation framework for a multi-tenant inventory and POS web application.

The framework is designed to support reliable and maintainable testing across:

* Web UI
* REST APIs
* Database validation
* CI/CD pipelines

## Technology Stack

* Java
* Selenium WebDriver
* WebDriverManager
* TestNG
* Maven
* Page Object Model (POM)
* Git & GitHub
* Postman / Newman
* SQL
* GitHub Actions

## Framework Architecture

The automation framework follows a layered architecture to improve maintainability, reusability, and scalability.

```text
Test Classes
     ↓
Page Objects
     ↓
Base Page
     ↓
Wait Utilities
     ↓
WebDriver
     ↓
Browser
```

Supporting components:

```text
DriverFactory (with WebDriverManager)
ConfigReader
TestNG
Maven
```

## Project Structure

```text
inventory/
│
├── pom.xml
├── README.md
├── .gitignore
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/electropi/inventory/
│   │           ├── base/
│   │           │   └── BasePage.java
│   │           │
│   │           ├── factory/
│   │           │   └── DriverFactory.java
│   │           │
│   │           ├── pages/
│   │           │   ├── LoginPage.java
│   │           │   ├── DashboardPage.java
│   │           │   └── InventoryPage.java
│   │           │
│   │           └── utils/
│   │               ├── ConfigReader.java
│   │               └── WaitUtils.java
│   │
│   └── test/
│       ├── java/
│       │   └── com/electropi/inventory/
│       │       ├── base/
│       │       │   └── BaseTest.java
│       │       │
│       │       └── tests/
│       │           └── InventoryTest.java
│       │
│       └── resources/
│           └── config.properties
│
├── api/
│   └── Inventory.postman_collection.json
│
├── database/
│   └── inventory_validation.sql
│
└── .github/
    └── workflows/
        └── qa-pipeline.yml
```

## UI Automation Flow

The main inventory UI flow is structured as:

```text
Login
  ↓
Dashboard
  ↓
Inventory
  ↓
Add Product
  ↓
Save
  ↓
Verify Success Message
```

## API Testing

API test scenarios live in `api/Inventory.postman_collection.json` and are organized into two folders:

* **POSITIVE** — valid item creation and a boundary case (`quantity = 0`)
* **NEGATIVE** — missing authorization, missing required field, invalid category reference

Authentication is handled dynamically via a collection-level pre-request script, which logs in and stores a bearer token before any authenticated request runs.

## Database Validation

`database/inventory_validation.sql` verifies that an item created through the API is correctly persisted and linked to its category via a join between the `products` and `categories` tables.

## CI/CD Pipeline

`.github/workflows/qa-pipeline.yml` defines a two-stage pipeline: API tests run first via Newman, and UI tests run afterward only if the API stage passes, with test reports uploaded as build artifacts.

The workflow depends on `BASE_URL`, `STORE_ADMIN_USERNAME`, and `STORE_ADMIN_PASSWORD` being configured as GitHub repository secrets. These are not set in this repository, so the workflow will not complete successfully as-is — see Test Execution Status below.

## Test Execution

Run the Maven test suite with:

```bash
mvn clean test
```

## Test Execution Status

The `adminCanAddInventoryItem` test in `InventoryTest.java` is currently set to `@Test(enabled = false)`.

This is a deliberate decision, not an oversight. The test suite depends on three pieces of external configuration that are not currently available:

* `BASE_URL` — a live or staging URL for the application
* `STORE_ADMIN_USERNAME` — valid Store Admin credentials
* `STORE_ADMIN_PASSWORD` — valid Store Admin credentials

Without these, the test cannot reach a real login page, and running it would either fail at the environment-validation check (`Assert.assertNotNull`) or, if that check were removed, fail with a `NoSuchElementException` against a non-existent page — neither of which would demonstrate anything useful about the framework itself.

The test was temporarily enabled during development to confirm that the framework compiles, that Maven and TestNG pick it up correctly, and that `WebDriverManager` successfully resolves and launches a real Chrome session end-to-end. That run confirmed all of the above, and failed exactly where expected — at the environment-configuration check — which validates that `DriverFactory`, `BasePage`, `WaitUtils`, and `ConfigReader` are all wired together correctly.

The test has since been set back to `enabled = false` to avoid a build failure in the absence of real credentials. To run it against a live environment, set the three environment variables above and change the annotation back to `@Test`.

## Configuration

Application-specific configuration is separated from the test implementation.

Sensitive credentials should be supplied through environment variables and must not be stored in source control.

Example environment variables:

```text
BASE_URL
STORE_ADMIN_USERNAME
STORE_ADMIN_PASSWORD
```

## Test Design Principles

The framework is designed around:

* Page Object Model
* Reusable components
* Explicit waits
* Stable locators
* Separation of test logic and UI implementation
* Centralized WebDriver management
* Secure configuration handling
* Maintainable test structure

## Automation Layers

```text
UI Automation        — implemented (src/)
API Automation        — implemented (api/)
Database Validation   — implemented (database/)
CI/CD Integration      — implemented (.github/workflows/)
```

## Author

Marwa Essam