# Spring Boot Blog Platform

A comprehensive and secure RESTful blog platform built with Spring Boot, featuring JWT authentication, role-based access control, and complete CRUD operations for managing users, posts, categories, and comments.

##  Overview

This application provides a robust backend service for a blogging platform with enterprise-grade security implementations, file upload capabilities, and a well-structured API design following REST principles.

##  Architecture & Technology Stack

### **Core Technologies**
- **Java**: 17
- **Spring Boot**: 3.3.1
- **Spring Security**: JWT-based authentication
- **Spring Data JPA**: ORM and database operations
- **MySQL**: Primary database
- **Maven**: Dependency management and build tool

### **Key Dependencies**
- **Spring Boot Starter Web**: RESTful web services
- **Spring Boot Starter Data JPA**: Database persistence
- **Spring Boot Starter Security**: Authentication and authorization
- **Spring Boot Starter Validation**: Input validation
- **JWT (JJWT)**: 0.11.5 - Token-based authentication
- **MySQL Connector**: 8.0.33
- **Lombok**: Code generation
- **ModelMapper**: 3.2.0 - Object mapping
- **Spring Boot DevTools**: Development productivity

##  Security Implementation

### **Authentication & Authorization**
- **JWT Token-based Authentication**: Stateless authentication mechanism
- **Token Validity**: 5 hours (configurable)
- **Password Encryption**: BCrypt hashing algorithm
- **Role-based Access Control**: ADMIN role implementation

### **Security Features**
1. **JWT Helper Service** (`JwtHelper.java`):
   - Token generation and validation
   - Claims extraction and verification
   - Expiration handling

2. **JWT Authentication Filter** (`JwtAuthenticationFilter.java`):
   - Request interception and token validation
   - Security context configuration
   - Comprehensive error handling for expired/malformed tokens

3. **Security Configuration** (`SecurityConfig.java`):
   - Password encoder configuration
   - In-memory user details service
   - Authentication manager setup

4. **JWT Authentication Entry Point**:
   - Unauthorized access handling
   - Custom error responses

### **Security Headers & Validation**
- Authorization header validation (`Bearer` token format)
- Input validation using `@Valid` annotations
- Exception handling for security violations

##  Database Schema & Entity Relationships

### **Entity Relationship Diagram**
```
User (1) -----> (N) Post (N) -----> (1) Category
                     |
                     v
                (N) Comment
```

### **Entities**

#### **User Entity** (`User.java`)
```sql
Table: users
- id (PK, AUTO_INCREMENT)
- user_name (VARCHAR(50), NOT NULL)
- email (VARCHAR)
- password (VARCHAR)
- about (TEXT)
```
**Relationships**: One-to-Many with Posts

#### **Post Entity** (`Post.java`)
```sql
Table: posts
- postId (PK, IDENTITY)
- post_title (VARCHAR(100), NOT NULL)
- description (VARCHAR(100))
- imageName (VARCHAR)
- addedDate (DATE)
- category_id (FK)
- user_id (FK)
```
**Relationships**:
- Many-to-One with User
- Many-to-One with Category
- One-to-Many with Comments

#### **Category Entity** (`Category.java`)
```sql
Table: categories
- categoryId (PK, IDENTITY)
- title (VARCHAR(100), NOT NULL)
- description (TEXT)
```
**Relationships**: One-to-Many with Posts

#### **Comment Entity** (`Comment.java`)
```sql
Table: comments
- id (PK, IDENTITY)
- content (TEXT)
- post_id (FK)
```
**Relationships**: Many-to-One with Post

## API Documentation

### **Base URL**: `http://localhost:8080`

### **Authentication Endpoints**

#### **POST** `/auth/login`
**Description**: Authenticate user and generate JWT token
```json
Request Body:
{
    "email": "admin",
    "password": "admin"
}

Response:
{
    "jwtToken": "eyJhbGciOiJIUzUxMiJ9...",
    "username": "admin"
}
```

### **User Management Endpoints**

#### **POST** `/api/users`
**Description**: Create a new user
**Authorization**: Required
```json
Request Body:
{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "about": "Software Developer"
}
```

#### **GET** `/api/users`
**Description**: Retrieve all users
**Authorization**: Required

#### **GET** `/api/users/{userId}`
**Description**: Retrieve user by ID
**Authorization**: Required

#### **PUT** `/api/users/{userId}`
**Description**: Update user information
**Authorization**: Required

#### **DELETE** `/api/users/{userId}`
**Description**: Delete a user
**Authorization**: Required

### **Post Management Endpoints**

#### **POST** `/api/user/{userId}/category/{categoryId}/posts`
**Description**: Create a new post
**Authorization**: Required
```json
Request Body:
{
    "title": "My First Blog Post",
    "content": "This is the content of my blog post...",
    "imageName": "image.jpg"
}
```

#### **GET** `/api/posts`
**Description**: Retrieve all posts with pagination
**Query Parameters**:
- `pageNumber` (default: 0)
- `pageSize` (default: 10)
- `sortBy` (default: "postId")
- `sortDir` (default: "ASC")

#### **GET** `/api/posts/{postId}`
**Description**: Retrieve post by ID

#### **GET** `/api/user/{userId}/posts`
**Description**: Retrieve posts by user

#### **GET** `/api/category/{categoryId}/posts`
**Description**: Retrieve posts by category

#### **PUT** `/api/posts/{postId}`
**Description**: Update a post
**Authorization**: Required

#### **DELETE** `/api/posts/{postId}`
**Description**: Delete a post
**Authorization**: Required

#### **GET** `/api/posts/search/{search}`
**Description**: Search posts by content

### **File Upload Endpoints**

#### **POST** `/api/posts/upload/image/{postId}`
**Description**: Upload image for a post
**Content-Type**: `multipart/form-data`
**Form Parameter**: `image` (file)

#### **GET** `/api/posts/image/{imageName}`
**Description**: Retrieve uploaded image
**Response**: Image file (JPEG)

### **Category Management Endpoints**

#### **POST** `/api/categories`
**Description**: Create a new category
```json
Request Body:
{
    "categoryTitle": "Technology",
    "categoryDescription": "Posts about technology and programming"
}
```

#### **GET** `/api/categories`
**Description**: Retrieve all categories

#### **GET** `/api/categories/{categoryId}`
**Description**: Retrieve category by ID

#### **PUT** `/api/categories/{categoryId}`
**Description**: Update a category

#### **DELETE** `/api/categories/{categoryId}`
**Description**: Delete a category

### **Comment Management Endpoints**

#### **POST** `/api/post/{postId}/comments`
**Description**: Create a comment on a post
```json
Request Body:
{
    "content": "Great article! Very informative."
}
```

#### **DELETE** `/api/comments/{commentId}`
**Description**: Delete a comment

## Application Configuration

### **Environment Variables Configuration**

The application uses environment variables for secure configuration management. All sensitive data like database credentials and JWT secrets are externalized.

### **Configuration Files**
- `application.properties` - Main configuration with environment variable placeholders
- `.env.example` - Template for environment variables
- `.gitignore` - Comprehensive exclusion rules (including `.env` files)

### **Key Environment Variables**
```bash
# Server Configuration
SERVER_PORT=8080

# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/blog_app_apis
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password

# JWT Security
JWT_SECRET=your_super_secret_jwt_key_here
JWT_EXPIRATION=18000

# File Upload
MAX_FILE_SIZE=10MB
IMAGE_UPLOAD_PATH=images/

# Logging
SECURITY_LOG_LEVEL=INFO
```

### **Security Best Practices Implemented**
-  **Environment Variables**: All sensitive data externalized
-  **JWT Secret**: Configurable secret key for token signing
-  **Database Credentials**: Not hardcoded in configuration
-  **Git Ignore**: Comprehensive `.gitignore` prevents credential leakage
-  **Default Values**: Fallback values for development
-  **Template File**: `.env.example` for easy setup

##  Getting Started

### **Prerequisites**
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### **Installation & Setup**

1. **Clone the repository**
```bash
git clone <repository-url>
cd springboot-blog-platform
```

2. **Environment Configuration**
```bash
# Copy the environment template
cp .env.example .env

# Edit .env with your actual values
nano .env  # or use your preferred editor
```

3. **Configure Environment Variables**
Update `.env` file with your specific values:
```bash
# Database Configuration
DB_USERNAME=your_actual_db_username
DB_PASSWORD=your_actual_db_password

# JWT Security (IMPORTANT: Change in production)
JWT_SECRET=your_super_secure_jwt_secret_minimum_256_bits

# Server Configuration
SERVER_PORT=8080
```

4. **Database Setup**
```sql
CREATE DATABASE blog_app_apis;
```

5. **Generate Secure JWT Secret (Recommended)**
```bash
# Generate a secure JWT secret
openssl rand -base64 64
# Copy the output to JWT_SECRET in your .env file
```

6. **Build the application**
```bash
mvn clean install
```

7. **Run the application**
```bash
# Option 1: Using Maven (loads .env automatically if using dotenv)
mvn spring-boot:run

# Option 2: Using Java with environment variables
export $(grep -v '^#' .env | xargs) && mvn spring-boot:run

# Option 3: Set variables manually
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
export JWT_SECRET=your_jwt_secret
mvn spring-boot:run
```

The application will start on `http://localhost:8080` (or your configured SERVER_PORT)

### **Default Users**
- **Username**: `admin`, **Password**: `admin`, **Role**: ADMIN
- **Username**: `dhriti`, **Password**: `Dhriti@123`, **Role**: ADMIN

## API Usage Examples

### **1. Authentication**
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin","password":"admin"}'
```

### **2. Create a Post** (with JWT token)
```bash
curl -X POST http://localhost:8080/api/user/1/category/1/posts \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"title":"My Blog Post","content":"Post content..."}'
```

### **3. Upload Image**
```bash
curl -X POST http://localhost:8080/api/posts/upload/image/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -F "image=@/path/to/image.jpg"
```

## Key Features

###  Implemented Features
-  **JWT-based Authentication & Authorization**
- **Environment Variables Configuration**
-  **Secure Credential Management**
-  **User Management (CRUD operations)**
-  **Post Management with Categories**
-  **Comment System**
-  **File Upload (Image handling)**
-  **Pagination & Sorting**
-  **Search Functionality**
-  **Input Validation**
-  **Exception Handling**
-  **Role-based Access Control**
-  **Password Encryption (BCrypt)**
-  **RESTful API Design**
-  **Comprehensive .gitignore**
-  **Production-ready Configuration**

### ** Advanced Features**
- **Pagination**: Efficient data retrieval with customizable page size
- **Sorting**: Multi-field sorting capability
- **Search**: Content-based post search
- **File Management**: Image upload and serving
- **Security**: Comprehensive JWT implementation with configurable secrets
- **Environment Management**: Secure externalized configuration
- **Validation**: Request body validation with custom error handling
- **Exception Handling**: Global exception management
- **Production Ready**: Environment-specific configurations

##  Project Structure
```
springboot-blog-platform/
├── src/main/java/com/BlogApp/
│   ├── Security/                 # JWT & Security implementations
│   │   ├── JwtHelper.java           # JWT token operations (env-configured)
│   │   ├── JwtAuthenticationFilter.java
│   │   └── securityJwtAuthenticationEntryPoint.java
│   ├── config/                   # Configuration classes
│   │   ├── AppConfig.java
│   │   └── SecurityConfig.java
│   ├── controller/               # REST Controllers
│   │   ├── AuthController.java
│   │   ├── UserController.java
│   │   ├── PostController.java
│   │   ├── CategoryController.java
│   │   └── CommentController.java
│   ├── entities/                 # JPA Entities
│   │   ├── User.java
│   │   ├── Post.java
│   │   ├── Category.java
│   │   └── Comment.java
│   ├── services/                 # Business Logic
│   │   └── Impl/                # Service Implementations
│   ├── repositories/             # Data Access Layer
│   ├── payloads/                # DTOs and Request/Response models
│   ├── exception/               # Custom Exception Handling
│   └── BlogAppApplication.java  # Main Application Class
├── src/main/resources/
│   └── application.properties   # Environment-configured properties
├── .env.example                 # Environment variables template
├── .gitignore                   # Comprehensive git exclusions
├── pom.xml                      # Maven dependencies
└── README.md                    # This documentation
```

##  Security & Environment Management

### **Files Added for Security**
- **`.env.example`** - Template for environment variables
- **`.gitignore`** - Updated to exclude sensitive files
- **`application.properties`** - Modified to use environment variables
- **`JwtHelper.java`** - Updated to use configurable JWT secret

### **Environment Variables Usage**
All sensitive configuration moved to environment variables:
- Database credentials
- JWT secret keys
- Server ports
- File upload paths
- Logging levels
