# Banking API

A Spring Boot banking application that allows users to deposit, withdraw, and transfer funds between accounts. It stores user and account data in a MySQL database.

I built this project to gain hands-on practical experience with Spring Boot, Hibernate, and JPA. The most challenging part was implementing the account and transaction logic — making sure balances updated correctly and transfers between accounts were handled safely.

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Security + JWT
- Spring Data JPA + Hibernate
- MySQL
- Swagger/OpenAPI Documentation

## Features

- User registration and login with JWT authentication
- BCrypt password hashing
- Create bank accounts
- Deposit and withdraw funds
- Transfer funds between accounts
- Insufficient funds protection

## API Endpoints

### Authentication
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/auth/register | Register a new user |
| POST | /api/auth/login | Login and receive JWT token |

### Accounts
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/accounts | Create a bank account |
| GET | /api/accounts/{id} | Get account details |
| POST | /api/accounts/{id}/deposit | Deposit funds |
| POST | /api/accounts/{id}/withdraw | Withdraw funds |
| POST | /api/accounts/transfer | Transfer between accounts |

## Running the Project

### Prerequisites
- Java 17
- MySQL
- Maven

### Setup

1. Clone the repository
```bash
git clone https://github.com/Paribesh101/banking-api.git
```

2. Create a MySQL database
```sql
CREATE DATABASE banking_db;
```

3. Update `src/main/resources/application.properties` with your MySQL credentials

4. Run the application
```bash
./mvnw spring-boot:run
```

5. Access Swagger documentation at `http://localhost:8080/swagger-ui.html`
