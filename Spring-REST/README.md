# Spring REST API Example

This project demonstrates a RESTful API built with Spring Boot, featuring product management with reviews. It includes entity relationships, DTOs, and repository patterns.

## Prerequisites

- Java 21
- Maven 3.9.x
- Your favorite IDE (IntelliJ IDEA, Eclipse, or VS Code)
- [Bruno](https://www.usebruno.com/) for API testing (optional)

## Tech Stack

- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Lombok
- Maven

## Project Structure

```
Spring-REST/
├── src/
│   ├── main/
│   │   ├── java/co/smarttechie/
│   │   │   ├── controller/
│   │   │   │   └── ProductController.java
│   │   │   ├── entity/
│   │   │   │   ├── Product.java
│   │   │   │   └── Review.java
│   │   │   ├── model/
│   │   │   │   ├── Product.java
│   │   │   │   └── Review.java
│   │   │   ├── repository/
│   │   │   │   ├── ProductRepository.java
│   │   │   │   └── ReviewRepository.java
│   │   │   ├── service/
│   │   │   │   └── ProductService.java
│   │   │   └── SpringRestApplication.java
│   │   └── resources/
│   │       └── application.properties
├── rest-collection/
│   └── spring-rest-api/
│       ├── product/
│       │   ├── create/
│       │   ├── add-review/
│       │   └── get/
│       └── bruno.json
├── .gitignore
├── pom.xml
└── README.md
```

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/2013techsmarts/Code_Samples.git
   cd Spring-REST
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on http://localhost:8080

## API Endpoints

### Products

1. Create Product
   ```http
   POST /api/products
   Content-Type: application/json

   {
     "name": "iPhone 15 Pro",
     "salePrice": 999.99
   }
   ```

2. Get Product
   ```http
   GET /api/products/{id}
   ```

3. Add Review to Product
   ```http
   POST /api/products/{productId}/reviews
   Content-Type: application/json

   {
     "comment": "Great phone with amazing camera!",
     "rating": 5,
     "reviewerName": "John Doe"
   }
   ```

## Testing with Bruno

1. Install Bruno from [usebruno.com](https://www.usebruno.com/)
2. Open Bruno and click "Import Collection"
3. Navigate to `rest-collection/spring-rest-api` and select it
4. Execute requests in sequence:
   - Create Product
   - Add Review (uses product ID from create response)
   - Get Product

## Database

The application uses H2 in-memory database by default. The database console is available at:
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (leave empty)

## Entity Relationships

- `Product` ⟷ `Review`: One-to-Many bidirectional relationship
  - A Product can have multiple Reviews
  - Each Review belongs to one Product
