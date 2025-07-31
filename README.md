# SpringBoot-JOOQ-SOLID

A Java RESTful API project built with **Spring Boot**, **JOOQ**, and **SOLID principles**. This application demonstrates clean architectural patterns, modular design, and uses Liquibase for database migrations.

---

## 📁 Project Structure (Partial)
```
src/
├── main/
│   ├── java/com/jooqspring/springjooq/
│   │   ├── controllers/         # REST controllers (User, Root)
│   │   ├── dto/                 # Request, response, repository DTOs
│   │   ├── entity/              # JPA or domain entities (User, Book, etc.)
│   │   ├── exceptions/          # Custom exceptions & handlers
│   │   ├── interceptor/         # Request interceptors (if any)
│   │   ├── interfaces/          # Service interface contracts
│   │   ├── repositories/        # JOOQ-based data access layer
│   │   └── services/            # Business logic
│   └── resources/
│       ├── application.properties
│       └── db/changelog/migration/  # Liquibase migration XMLs
└── test/
    └── SpringjooqApplicationTests.java
```

---

## ✨ Features

- ✅ Spring Boot 3.x
- ✅ JOOQ for type-safe SQL access
- ✅ MySQL (or PostgreSQL) database integration
- ✅ Clean architecture with SOLID principles
- ✅ DTO-based request/response models
- ✅ Centralized exception handling
- ✅ Pagination support
- ✅ Liquibase for schema versioning
- ✅ Easy to extend and maintain

---

## 📦 Tech Stack

| Layer             | Library                  |
|------------------|--------------------------|
| Core Framework    | Spring Boot              |
| Database Access   | JOOQ (code-generated)     |
| Database          | MySQL / PostgreSQL       |
| Migrations        | Liquibase                |
| API Style         | REST (JSON)              |
| Architecture      | SOLID (Single Responsibility, etc.) |

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL / PostgreSQL

### Build & Run

```bash
# Generate JOOQ code (important!)
mvn generate-sources

# Build project
./mvnw clean install

# Run Spring Boot application
./mvnw spring-boot:run
```

---

## 🔗 API Endpoints

| Method | Endpoint         | Description        |
|--------|------------------|--------------------|
| GET    | /api/users       | List users         |
| POST   | /api/users       | Create user        |
| GET    | /api/books       | List books         |
| GET    | /api/borrow      | List borrow data   |

---

## 🧰 Tools Used

- **Spring Boot** - Web framework
- **JOOQ** - Type-safe SQL and code generation
- **Liquibase** - DB versioning & migrations
- **Lombok** - Boilerplate killer
- **JUnit** - Unit testing

---

## ✅ To Do

- [ ] Add Swagger UI
- [ ] Add JWT authentication
- [ ] Add unit tests (JUnit 5 + Mockito)
- [ ] Dockerize the app

---

## 📄 License

MIT © 2025 Exel Tarkus
