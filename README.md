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
DriverFactory
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
└── src/
    ├── main/
    │   └── java/
    │       └── com/electropi/inventory/
    │           ├── base/
    │           │   └── BasePage.java
    │           │
    │           ├── factory/
    │           │   └── DriverFactory.java
    │           │
    │           ├── pages/
    │           │   ├── LoginPage.java
    │           │   ├── DashboardPage.java
    │           │   └── InventoryPage.java
    │           │
    │           └── utils/
    │               ├── ConfigReader.java
    │               └── WaitUtils.java
    │
    └── test/
        ├── java/
        │   └── com/electropi/inventory/
        │       ├── base/
        │       │   └── BaseTest.java
        │       │
        │       └── tests/
        │           └── InventoryTest.java
        │
        └── resources/
            └── config.properties
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

## Test Execution

Run the Maven test suite with:

```bash
mvn clean test
```

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

## Future Automation Layers

The framework will be extended to include:

```text
UI Automation
     ↓
API Automation
     ↓
Database Validation
     ↓
CI/CD Integration
```

## Author

Marwa Essam
