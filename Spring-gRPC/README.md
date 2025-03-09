# Spring Boot gRPC Service

This project demonstrates the implementation of a gRPC service using Spring Boot, featuring product and review management functionality with an H2 database backend.

## Prerequisites

- Java 23
- Maven 3.6.3 or higher
- Git (optional, for cloning the repository)

## Project Structure

```
Spring-gRPC/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── co/smarttechie/
│   │   │       ├── entity/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── SpringGRpcApplication.java
│   │   ├── proto/
│   │   │   └── product.proto
│   │   └── resources/
│   │       └── application.yml
│   └── test/
└── pom.xml
```

## Technology Stack

- Spring Boot 3.4.3
- gRPC 1.62.2
- Protocol Buffers 3.25.3
- Spring Data JPA
- H2 Database
- Project Lombok
- Maven

## Setup and Installation

1. **Clone the Repository** (if using Git)
   ```bash
   git clone https://github.com/2013techsmarts/Code_Samples
   cd Spring-gRPC
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

## Running the Application

1. **Start the Application**
   ```bash
   mvn spring-boot:run
   ```
   This will:
   - Start the Spring Boot application
   - Initialize the H2 database
   - Start the gRPC server

## gRPC Service Definitions

The service is defined in `src/main/proto/product.proto` and includes:

### Messages
- `Product` - Contains product details (id, name, sale_price, reviews)
- `Review` - Contains review details (id, comment, rating, reviewer_name)
- Various request/response messages for different operations

### Services
```protobuf
service ProductService {
  rpc CreateProduct(CreateProductRequest) returns (CreateProductResponse);
  rpc GetProduct(GetProductRequest) returns (GetProductResponse);
  rpc AddReview(AddReviewRequest) returns (AddReviewResponse);
  rpc GetProductReviews(GetProductReviewsRequest) returns (GetProductReviewsResponse);
}
```

## Database Schema

The application uses an H2 in-memory database with the following tables:

### Products Table
```sql
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    sale_price DOUBLE
);
```

### Reviews Table
```sql
CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment VARCHAR(255),
    rating INTEGER,
    reviewer_name VARCHAR(255),
    product_id BIGINT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

## Features

- Product Management:
  - Create new products
  - Retrieve products by ID
- Review Management:
  - Add reviews to products
  - Retrieve product reviews

## Technical Details

- Uses Java Records for DTOs
- Implements gRPC service with Spring Boot integration
- Utilizes JPA for database operations
- Supports bidirectional relationships between products and reviews
- Runs on Java 23 with preview features enabled

## Additional Notes

- The application uses an in-memory H2 database that resets on restart
- Lombok is used to reduce boilerplate code in entity classes
- The project demonstrates proper separation of concerns with entity and model classes
- gRPC code is automatically generated during the Maven build process

