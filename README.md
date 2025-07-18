# 🔐 Algorizzmus Auth Service

A comprehensive authentication and user management service built for modern applications. This service provides secure authentication, user registration, profile management, and authorization capabilities.

## ✨ Features

- **User Registration & Authentication**
    - Email/password registration
    - Secure login with JWT tokens
    - Password reset functionality
    - Email verification

- **User Management**
    - Profile creation and updates
    - Account deactivation/reactivation
    - User role and permission management

- **Security**
    - JWT-based authentication
    - Password hashing with bcrypt

- **Session Management**
    - Token refresh mechanism
    - Session invalidation
    - Multi-device session handling

## 🚀 Getting Started

### 📋 Prerequisites

- Java 17 or higher
- Gradle 7.0 or higher
- PostgreSQL
- SMTP server (for email verification)

### 🔧 Installation

1. Clone the repository:
```bash
git clone https://github.com/your-org/algorizzmus-auth-service.git
cd algorizzmus-auth-service
```

2. Build the project:
```bash
./gradlew build
```

1. Set up environment variables:
```bash
cp .env.example .env
# Edit .env with your configuration
```

4. Run the application:
```bash
./gradlew bootRun
```

Or run the JAR file:
```bash
java -jar build/libs/algorizzmus-auth-service-1.0.0.jar
```

The service will be available at `http://localhost:8080`

## ⚙️ Configuration

### 🔐 Environment Variables

| Variable              | Description        | Required |
|-----------------------|--------------------|----------|
| `DATASOURCE_PASSWORD` | Database password  | Yes      |
| `JWT_SECRET`          | JWT signing secret | Yes      |

### 🗄️ Database Configuration

The service uses Spring Boot's auto-configuration for PostgreSQL connections. Configure your database connection in `application.properties` or `application.yml`, with the password provided via the `DATASOURCE_PASSWORD` environment variable.

## 🌐 API Endpoints

### 🔑 Authentication

#### POST `/users/register`
Register a new user account.

**Request Body:**
```json
{
  "username": "user",
  "password": "securePassword123"
}
```

#### POST `/users/login`
Authenticate user and receive JWT token.

**Request Body:**
```json
{
  "username": "user",
  "password": "securePassword123"
}
```

#### POST `/users/refresh`
Refresh JWT token.

**Headers:**
```
Authorization: Bearer <refresh_token>
```

#### POST `/api/auth/logout`
Logout user and invalidate session.

#### POST `/api/auth/forgot-password`
Request password reset email.

#### POST `/api/auth/reset-password`
Reset password with token.

## 🔒 Authentication Flow

1. User registers with email and password
2. Email verification sent
3. User logs in with credentials
4. JWT access token and refresh token returned
5. Access token used for authenticated requests
6. Refresh token used to obtain new access tokens

## 🛡️ Security Features

- Passwords are hashed using bcrypt with salt rounds
- JWT tokens are signed and verified
- Rate limiting prevents brute force attacks
- Input validation prevents injection attacks
- CORS protection for cross-origin requests
- Secure HTTP headers with helmet.js

## 💻 Development

### 🧪 Running Tests

```bash
./gradlew test
```

### 📦 Building for Production

```bash
./gradlew build -x test
```

## 🚢 Deployment

### Docker Compose

```bash
docker-compose up -d
```

## Monitoring and Logging

The service includes structured logging and health check endpoints:

- Health check: `GET /health`
- Metrics: `GET /metrics`
- Logs are output in JSON format for easy parsing

## ❌ Error Handling

The service returns consistent error responses

## 🤝 Contributing

1. Clone the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Run the test suite
6. Submit a pull request
