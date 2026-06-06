# Banking API

A RESTful banking API built with Java 17 and Spring Boot, featuring JWT authentication, account management, and financial transaction processing.

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
- Input validation and error handling

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

## Example Requests

**Register a user**
```json
POST /api/auth/register
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "secret123"
}
```

**Login**
```json
POST /api/auth/login
{
  "email": "john@example.com",
  "password": "secret123"
}
```

**Create an account**
```json
POST /api/accounts
{
  "userId": 1
}
```

**Deposit funds**
```json
POST /api/accounts/1/deposit
{
  "amount": 500
}
```

**Transfer funds**
```json
POST /api/accounts/transfer
{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 100
}
```
