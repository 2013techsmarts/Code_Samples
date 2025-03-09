# Spring Boot gRPC Service

This project demonstrates the implementation of a gRPC service using Spring Boot, featuring product and review management functionality with an H2 database backend. The project includes both a Java-based gRPC server and a Node.js gRPC client.

## Prerequisites

### Server
- Java 23
- Maven 3.6.3 or higher

### Client
- Node.js 18+
- npm 9+

## Project Structure

```
Spring-gRPC/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── co/smarttechie/
│   │   │       ├── entity/          # JPA entities
│   │   │       ├── model/           # Data transfer objects
│   │   │       ├── repository/      # Spring Data repositories
│   │   │       ├── service/         # gRPC service implementations
│   │   │       └── config/          # Configuration classes
│   │   ├── proto/                   # Protocol buffer definitions
│   │   │   └── product.proto
│   │   └── resources/
│   │       └── application.yml      # Application configuration
│   └── test/
├── client/                          # Node.js gRPC client
│   ├── proto/                       # Copied proto files for client
│   ├── index.js                     # Client implementation
│   └── package.json                 # Node.js dependencies
└── pom.xml                         # Maven configuration
```

## Technology Stack

### Server
- Spring Boot 3.4.3
- gRPC 1.62.2
- Protocol Buffers 3.25.3
- Spring Data JPA
- H2 Database
- Project Lombok
- Maven

### Client
- Node.js
- @grpc/grpc-js
- @grpc/proto-loader

## Setup and Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/2013techsmarts/Code_Samples
   cd Spring-gRPC
   ```

2. **Build and Run the Server**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **Setup and Run the Client**
   ```bash
   cd client
   npm start
   ```

## gRPC Service Definitions

The service is defined in `src/main/proto/product.proto` and includes:

### Messages
```protobuf
message Product {
  int64 id = 1;
  string name = 2;
  double salePrice = 3;
  repeated Review reviews = 4;
}

message Review {
  int64 id = 1;
  string comment = 2;
  int32 rating = 3;
  string reviewerName = 4;
}
```

### Service Operations
```protobuf
service ProductService {
  rpc CreateProduct(CreateProductRequest) returns (CreateProductResponse);
  rpc GetProduct(GetProductRequest) returns (GetProductResponse);
  rpc AddReview(AddReviewRequest) returns (AddReviewResponse);
  rpc GetProductReviews(GetProductReviewsRequest) returns (GetProductReviewsResponse);
}
```

## Client Usage Example

```javascript
// Create a product
const productResponse = await createProduct('iPhone 15 Pro', 999.99);
const productId = productResponse.product.id;

// Add a review
const reviewResponse = await addReview(
    productId,
    'Great product, amazing camera!',
    5,
    'John Doe'
);

// Get product details with reviews
const productDetails = await getProduct(productId);
```

## Features

- Product Management:
  - Create new products with name and price
  - Retrieve products by ID with their reviews
- Review Management:
  - Add reviews to products with comments and ratings
  - Retrieve all reviews for a product

- In-Memory H2 Database

## Technical Details

- Uses Java Records for DTOs
- Implements gRPC service with Spring Boot integration
- Runs on Java 23 with preview features enabled
- Node.js client with Promise-based API


