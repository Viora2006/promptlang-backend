# PromptLang Backend

PromptLang Backend is a Spring Boot REST API that powers the PromptLang AI coding assistant. It handles user authentication, AI code generation requests, database persistence, and communication with the OpenAI API.

## Features

- User registration
- User login authentication
- Password hashing and verification
- AI-powered code generation
- AI-generated code explanations
- PostgreSQL database integration
- RESTful API architecture
- Chat history persistence
- Spring Data JPA repositories
- DTO-based request and response handling

## Tech Stack

### Backend Framework
- Java 17
- Spring Boot

### Security
- Spring Security
- BCrypt Password Hashing

### Database
- PostgreSQL
- Spring Data JPA
- Hibernate ORM

### AI Integration
- OpenAI API

### Build Tool
- Maven

## Project Structure

```text
src/main/java/app/promptlang

├── controller
│   ├── AuthController
│   └── ChatController
│
├── service
│   ├── AuthService
│   └── ChatService
│
├── repository
│   ├── UserRepository
│   └── ChatHistoryRepository
│
├── model
│   ├── User
│   └── ChatHistory
│
├── dto
│   ├── LoginRequest
│   ├── RegisterRequest
│   ├── ChatRequest
│   ├── AuthResponse
│   └── GeneratedCodeResponse
│
└── PromptLangApplication
```

## API Endpoints

### Register User

**POST**

```http
/api/auth/register
```

Request:

```json
{
  "username": "tyler",
  "password": "password123"
}
```

Response:

```json
{
  "message": "User Created"
}
```

---

### Login User

**POST**

```http
/api/auth/login
```

Request:

```json
{
  "username": "tyler",
  "password": "password123"
}
```

Response:

```json
{
  "message": "Login Successful"
}
```

---

### Generate Code

**POST**

```http
/api/chat
```

Request:

```json
{
  "username": "tyler",
  "message": "Create a Java Hello World program"
}
```

Response:

```json
{
  "code": "public class HelloWorld { ... }",
  "explanation": "This program prints Hello World."
}
```

## Architecture

```text
React Frontend
      ↓
HTTP Requests
      ↓
Spring Boot Controllers
      ↓
Service Layer
      ↓
PostgreSQL Database

      ↓
OpenAI API
```

## Database

PromptLang uses PostgreSQL for persistent storage.

Current entities include:

### Users

Stores:

- User ID
- Username
- Password Hash

### Chat History

Stores:

- Prompt
- Generated Code
- Explanation
- Timestamp
- Associated User

## Running Locally

### Clone Repository

```bash
git clone <repository-url>
cd promptlang-backend
```

### Configure Database

Create a PostgreSQL database:

```sql
CREATE DATABASE PromptLangDatabase;
```

Update:

```properties
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/PromptLangDatabase
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Configure OpenAI Key

Add your API key to:

```properties
application.properties
```

or use environment variables.

### Run Application

```bash
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

## Future Improvements

- JWT Authentication
- Refresh Tokens
- Role-Based Authorization
- Rate Limiting
- Docker Support
- Unit Testing
- Integration Testing
- Conversation Threads
- Conversation Search
- Cloud Deployment

## Author

Tyler Carrasco

Computer Science Student

California State Polytechnic University, Pomona