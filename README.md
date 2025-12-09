# LinkValut - Smart URL Shortener (Backend)

LinkValut is a smart URL shortening service built with Spring Boot that provides secure link management with user authentication and analytics tracking.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)

## Features

- **URL Shortening**: Create shortened URLs for easy sharing
- **Redirection**: Seamless redirection from short URLs to original URLs
- **User Authentication**: Secure login and registration with JWT tokens
- **Analytics**: Track clicks and gather statistics on shortened URLs
- **RESTful API**: Clean and well-documented API endpoints

## Tech Stack

- **Backend**: Spring Boot 3.5.7
- **Language**: Java 21
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Security**: Spring Security with JWT Authentication
- **Build Tool**: Maven

## Project Structure

```
src/main/java/com/url/shortner/
├── controller/          # REST controllers
├── dtos/                # Data Transfer Objects
├── models/              # JPA entities
├── repository/          # Spring Data JPA repositories
├── security/            # JWT security configuration
├── service/             # Business logic implementations
└── LinkValutApplication.java # Main application class
```

## Prerequisites

- Java 21
- Maven 3.8+
- MySQL Server 8.0+

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd LinkValut
   ```

2. Configure the database settings in `src/main/resources/application.properties`

3. Build the project:
   ```bash
   mvn clean install
   ```

## Configuration

Update the following properties in `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/{dbname}
spring.datasource.username=your_username
spring.datasource.password=your_password

# JWT Configuration
jwt.secret=your_jwt_secret_key
jwt.expiration=172800000
```

## Running the Application

Using Maven:
```bash
mvn spring-boot:run
```

Or using Java:
```bash
java -jar target/LinkValut-0.0.1-SNAPSHOT.jar
```

The application will start on port 8080 by default.

## API Endpoints

### Authentication

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/auth/public/register` | POST | User registration |
| `/api/auth/public/login` | POST | User login |

### URL Management

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/urls` | POST | Create a new short URL |
| `/api/urls` | GET | Get all URLs for authenticated user |
| `/api/urls/{id}` | GET | Get specific URL details |
| `/api/urls/{id}` | DELETE | Delete a URL |
| `/{shortCode}` | GET | Redirect to original URL |

## Database Schema

The application uses three main entities:

1. **User**: Stores user information (username, email, password, role)
2. **UrlMapping**: Stores URL mappings (original URL, short URL, creation date, click count)
3. **ClickEvent**: Tracks click events on shortened URLs (timestamp, user agent, IP address)

All tables are automatically created and managed by Hibernate based on the entity definitions.

## Frontend Integration

This backend provides a complete REST API that can be consumed by any frontend framework. The frontend will be added separately and will communicate with this backend through the documented API endpoints.