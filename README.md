# LinkVault - Smart URL Shortener

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring%20Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-green) ![React](https://img.shields.io/badge/React-19-blue)

LinkVault is a full-stack URL shortening service that provides secure, efficient link management with user authentication, analytics tracking, and a modern responsive UI. Create, manage, and track shortened URLs with detailed click analytics and statistics.

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [Live Deployment](#live-deployment)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security Features](#security-features)

## ✨ Features

**Core:** URL Shortening • Smart Redirection • JWT Authentication • Click Analytics • Date-range Filtering

**UI/UX:** Responsive Dashboard • Visual Analytics (Chart.js) • Copy-to-Clipboard • Form Validation • Notifications

**Security:** JWT Auth • Bcrypt Hashing • RBAC • Protected Endpoints • SQL Injection Prevention

## 🛠️ Tech Stack

**Backend:** Spring Boot 3.5.7 • Java 21 • Spring Data JPA • Spring Security • JWT (JJWT 0.13.0) • Maven

**Database:** MySQL (local development) • Neon (production)

**Frontend:** React 19.2.5 • Vite 8.0.10 • Tailwind CSS 3.4.1 • React Router 7.14.2 • Axios 1.15.2 • React Query 5.100.5 • React Hook Form 7.74.0 • Chart.js 4.5.1 • Material-UI 9.0.0

## 📁 Project Structure

```
├── Backend/                    # Spring Boot Backend
│   ├── src/main/java/com/url/shortner/
│   │   ├── controller/         # REST API endpoints
│   │   ├── service/            # Business logic
│   │   ├── repository/         # Data access (JPA)
│   │   ├── models/             # JPA entities
│   │   ├── dtos/               # Data transfer objects
│   │   └── security/           # JWT & Spring Security
│   └── pom.xml                 # Maven config
│
├── frontend/                   # React/Vite Frontend
│   ├── src/
│   │   ├── components/         # React components
│   │   ├── api/                # Axios configuration
│   │   ├── contextApi/         # React Context
│   │   ├── hooks/              # Custom hooks
│   │   └── utils/              # Helpers & constants
│   ├── public/                 # Static files
│   └── package.json            # npm dependencies
│
└── README.md                   # This documentation
```

## 📋 Prerequisites

- **Node.js** v16+ (frontend)
- **Java** 21 JDK (backend)
- **Maven** 3.8+ (backend build)
- **MySQL** 8.0+ (or a Neon PostgreSQL database)
- **Git** (version control)

Recommended: IntelliJ IDEA or VS Code, Postman for API testing

## 🚀 Installation & Setup

### Backend Setup

1. **Create Database**

   ```sql
   CREATE DATABASE linkvault;
   ```

2. **Configure** `Backend/src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/linkvault
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=your_secure_jwt_secret_key
   jwt.expiration=86400000
   frontend.url=http://localhost:5173
   ```

3. **Install Dependencies**
   ```bash
   cd Backend
   mvn clean install
   ```

### Frontend Setup

1. **Install Dependencies**

   ```bash
   cd frontend
   npm install
   ```

2. **Create** `frontend/.env`:

   ```env
   VITE_API_BASE_URL=http://localhost:8080
   VITE_APP_NAME=LinkVault
   VITE_REACT_SUBDOMAIN=http://url.localhost:5173
   VITE_REACT_FRONT_END_URL=http://localhost:5173

   ```

## ▶️ Running the Application

### Start MySQL

```bash
# Windows
net start MySQL80

# macOS
brew services start mysql@8.0

# Linux
sudo systemctl start mysql
```

### Start Backend

```bash
cd Backend
mvn spring-boot:run
```

Backend available at: `http://localhost:8080`

### Start Frontend (New Terminal)

```bash
cd frontend
npm run dev
```

Frontend available at: `http://localhost:5173`

## 🌐 Live Deployment

- **Frontend (Netlify):** [https://magical-stroopwafel-c1af6f.netlify.app/](https://magical-stroopwafel-c1af6f.netlify.app/)
- **Backend:** Deployed on Render
- **Database:** Hosted on Neon

Use the Netlify link above to access the live project.

## 📡 API Documentation

**Authentication:**

- `POST /api/auth/public/register` - Register new user
- `POST /api/auth/public/login` - Login user (returns JWT token)

**URL Management (Requires JWT):**

- `POST /api/urls/shorten` - Create shortened URL
- `GET /api/urls/myurls` - Get user's URLs
- `GET /api/urls/analytics/{shortUrl}` - Get click events by date range
- `GET /api/urls/totalClicks` - Get clicks aggregated by date

**Public:**

- `GET /{shortUrl}` - Redirect to original URL

Example (Shorten URL):

```bash
POST /api/urls/shorten
Authorization: Bearer {token}
Content-Type: application/json

{ "originalUrl": "https://example.com/very/long/url" }
```

#### Login User

```
POST /api/auth/public/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "secure_password123"
}

Response: 200 OK
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "username": "john_doe"
}
```

### URL Mapping Endpoints

## 🗄️ Database Schema

### Users Table

```sql
CREATE TABLE user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL UNIQUE,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) DEFAULT 'ROLE_USER',
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### URL Mappings Table

```sql
CREATE TABLE url_mapping (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  original_url LONGTEXT NOT NULL,
  short_url VARCHAR(255) NOT NULL UNIQUE,
  click_count INT DEFAULT 0,
  created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
```

### Click Events Table

```sql
CREATE TABLE click_event (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  short_url VARCHAR(255) NOT NULL,
  timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  user_agent VARCHAR(500),
  ip_address VARCHAR(50),
  url_mapping_id BIGINT NOT NULL,
  FOREIGN KEY (url_mapping_id) REFERENCES url_mapping(id) ON DELETE CASCADE
);
```

## 🔐 Security Features

- **JWT Authentication**: Stateless token-based auth
- **Spring Security**: Role-based access control
- **Password Hashing**: Bcrypt encryption
- **CORS Configuration**: Protected cross-origin requests
- **Input Validation**: DTOs and form validation
- **Protected Endpoints**: @PreAuthorize annotations
- **SQL Injection Prevention**: Parameterized JPA queries
