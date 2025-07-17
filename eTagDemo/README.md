# eTagDemo

A Spring Boot demo project showcasing how to implement ETag-based concurrency control in RESTful APIs using Spring Boot, JPA, and H2 in-memory database.

## Features
- RESTful API for managing `Item` resources
- ETag and `If-Match` header support for optimistic locking
- In-memory H2 database for easy setup and testing
- Integration tests using Spock Framework (Groovy)

## Requirements
- Java 17 or higher (project uses Java 24 in pom.xml)
- Maven 3.6+

## Getting Started

### Clone the repository
```bash
git clone [<repo-url>](https://github.com/2013techsmarts/Code_Samples.git)
cd eTagDemo
```

### Build and Run
```bash
./mvnw spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

### H2 Console
Access the H2 database console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: *(leave blank)*

## API Endpoints

### Create Item
- **POST** `/items`
- **Request Body:**
  ```json
  {
    "name": "Item Name",
    "description": "Item Description"
  }
  ```
- **Response:** 201 Created, returns created item with ETag header

### Get Item
- **GET** `/items/{id}`
- **Response:** 200 OK, returns item with ETag header

### Update Item (with ETag)
- **PUT** `/items/{id}`
- **Headers:**
  - `If-Match`: ETag value from previous GET/POST
- **Request Body:**
  ```json
  {
    "name": "Updated Name",
    "description": "Updated Description"
  }
  ```
- **Responses:**
  - 200 OK: Item updated, returns new ETag
  - 412 Precondition Failed: If ETag does not match current version
  - 404 Not Found: If item does not exist

#### Example cURL
```bash
# Create an item
curl -X POST http://localhost:8080/items \
  -H 'Content-Type: application/json' \
  -d '{"name":"Test Item","description":"A demo item"}' -i

# Get the item and ETag
curl -X GET http://localhost:8080/items/1 -i

# Update the item with ETag
curl -X PUT http://localhost:8080/items/1 \
  -H 'Content-Type: application/json' \
  -H 'If-Match: "1"' \
  -d '{"name":"Updated Name","description":"A demo item"}' -i
```

## Data Model

### Item
- `id`: Long
- `name`: String
- `description`: String
- `version`: Long (used for ETag)

## Running Tests

Integration tests are written using the Spock Framework (Groovy):
```bash
./mvnw test
```

*This project is for demonstration purposes and uses an in-memory database. For production, configure a persistent database and enhance security as needed.* 
