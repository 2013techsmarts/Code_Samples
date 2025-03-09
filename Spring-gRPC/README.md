# Spring Boot gRPC Application

This project demonstrates the implementation of a gRPC server using Spring Boot, featuring product management functionality with an H2 database backend.

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
│   │   │       ├── config/
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
- H2 Database
- Project Lombok
- Maven

## Setup and Installation

1. **Clone the Repository** (if using Git)
   ```bash
   git clone <repository-url>
   cd Spring-gRPC
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```
   This command will:
   - Generate Java classes from the Protocol Buffer definitions
   - Compile the project
   - Run tests
   - Create an executable JAR file

## Running the Application

1. **Start the Application**
   ```bash
   mvn spring-boot:run
   ```
   This will:
   - Start the Spring Boot application
   - Initialize the H2 database
   - Start the gRPC server on port 9090
   - Run a test client that creates a sample product

2. **Verify the Application**
   - The application will automatically run a test client on startup
   - You should see logs indicating:
     - Successful server startup
     - Database initialization
     - Sample product creation
     - gRPC communication success

## Testing the Application

The application includes a built-in test client that demonstrates:
- Creating a product (iPhone 15 Pro at $999.99)
- Receiving confirmation from the server
- Storing the product in the H2 database

Expected output for successful product creation:
```
Product created successfully!
Product ID: 1
Product Name: iPhone 15 Pro
Product Price: $999.99
Server Message: Product created successfully
```

## Configuration

Key configuration files:
- `application.yml`: Contains Spring Boot and H2 database configurations
- `pom.xml`: Project dependencies and build configurations
- `product.proto`: gRPC service and message definitions

## Database

The application uses an H2 in-memory database:
- Database URL: jdbc:h2:mem:productdb
- Console URL: http://localhost:8080/h2-console (when enabled)
- Tables:
  - products (id, name, sale_price)

## Troubleshooting

1. **Java Version Issues**
   - Ensure Java 23 is installed: `java -version`
   - Verify JAVA_HOME is set correctly

2. **Build Failures**
   - Clear Maven cache: `mvn clean`
   - Update Maven dependencies: `mvn dependency:resolve`
   - Verify protobuf compiler installation

3. **Runtime Issues**
   - Check port 9090 is available for gRPC server
   - Verify H2 database configuration
   - Review application logs for errors

## Additional Notes

- gRPC server runs on port 9090 by default
- The H2 database is in-memory and will be reset on application restart
- Lombok is used to reduce boilerplate code

## Support

For issues and questions:
1. Check the troubleshooting section
2. Review application logs
3. Verify configuration files
4. Check port availability 