# Spring Boot REST API

This project demonstrates the implementation of a REST API using Spring Boot, featuring product management functionality with an H2 database backend.

## Prerequisites

- Java 17
- Maven 3.6.3 or higher
- Git (optional, for cloning the repository)

## Project Structure

```
Spring-REST/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── co/smarttechie/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── SpringRestApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
└── pom.xml
```

## Technology Stack

- Spring Boot 3.4.3
- Spring MVC
- Spring Data JPA
- H2 Database
- Project Lombok
- Maven

## Setup and Installation

1. **Clone the Repository** (if using Git)
   ```bash
   git clone <repository-url>
   cd Spring-REST
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
   - Start the REST server on port 8080

2. **Access the Application**
   - The REST API will be available at: http://localhost:8080
   - H2 Console will be available at: http://localhost:8080/h2-console

## Testing the API

You can test the REST endpoints using curl or any API testing tool like Postman:

1. **Create a Product**
   ```bash
   curl -X POST http://localhost:8080/api/products \
   -H "Content-Type: application/json" \
   -d '{
       "name": "iPhone 15 Pro",
       "salePrice": 999.99
   }'
   ```

2. **Get a Product**
   ```bash
   curl http://localhost:8080/api/products/1
   ```

Expected Response Format:
```json
{
    "id": 1,
    "name": "iPhone 15 Pro",
    "salePrice": 999.99
}
```

## API Endpoints

- POST `/api/products` - Create a new product
- GET `/api/products/{id}` - Get a product by ID

## Database

The application uses an H2 in-memory database:
- Database URL: jdbc:h2:mem:productdb
- Console URL: http://localhost:8080/h2-console
- Username: sa
- Password: (empty)
- Tables:
  - products (id, name, sale_price)

## Additional Notes

- The application uses an in-memory H2 database that resets on restart
- Input validation is implemented using Jakarta Validation
- Lombok is used to reduce boilerplate code

## Support

For issues and questions:
1. Check the API documentation
2. Review application logs
3. Verify database connection
4. Check port availability 