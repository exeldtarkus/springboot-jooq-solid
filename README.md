# SpringBoot-JOOQ-SOLID

A clean and scalable Java RESTful API built with **Spring Boot**, **JOOQ**, and **SOLID principles**.  
Designed for high performance, modularity, and maintainability using best practices of modern Java backend development.

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
- Maven
- MySQL / PostgreSQL

### Clone the Project

```bash
git clone https://github.com/yourusername/springboot-jooq-solid.git
cd springboot-jooq-solid
```

### Configuration

Edit your `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dbname
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
```

---

## 🧪 Run the App

```bash
./mvnw spring-boot:run
```

---

## 🗃 Example Endpoints

| Method | Endpoint         | Description        |
|--------|------------------|--------------------|
| GET    | `/api/users`     | List all users     |
| POST   | `/api/users`     | Create new user    |
| GET    | `/api/books`     | List all books     |

---

## 🛠 Project Structure

```
src/
├── controllers/
├── services/
├── repositories/
├── dto/
├── entity/
├── config/
└── exceptions/
```

---

## 📚 SOLID Implementation

- **S** - Single Responsibility: Each layer does one thing (e.g. DTO, Controller, Service)
- **O** - Open/Closed: Easily extendable (e.g. add features via interfaces)
- **L** - Liskov: Services follow interface-based abstraction
- **I** - Interface Segregation: Separate interfaces (IUserService, IBookService)
- **D** - Dependency Inversion: Components depend on abstraction, not implementation

---

## ✅ To Do

- [ ] Add Swagger UI
- [ ] Add JWT authentication
- [ ] Add unit tests (JUnit 5 + Mockito)
- [ ] Dockerize the app

---

## 📄 License

MIT © [Your Name or Company]