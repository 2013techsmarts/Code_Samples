# Spring GraphQL Demo

This project demonstrates a GraphQL API implementation using Spring Boot for a Product Review system.

## Technologies Used

- Java 23
- Spring Boot 3.4.3
- Spring GraphQL
- Spring Data JPA
- H2 Database
- Lombok
- Maven

## Project Structure

```
src/main/java/co/smarttechie/
├── entity/
│   ├── Product.java
│   └── Review.java
├── repository/
│   ├── ProductRepository.java
│   └── ReviewRepository.java
├── service/
│   ├── ProductService.java
│   └── ReviewService.java
├── resolver/
│   ├── ProductResolver.java
│   └── ReviewResolver.java
└── SpringGraphQlApplication.java
```

## GraphQL Schema

```graphql
type Product {
    id: ID!
    name: String!
    description: String
    salePrice: Float!
    reviews: [Review]
}

type Review {
    id: ID!
    reviewerName: String!
    comment: String!
    rating: Int!
    product: Product!
}

type Query {
    productById(id: ID!): Product
    allProducts: [Product]!
}

type Mutation {
    createProduct(input: CreateProductInput!): Product!
    addReview(input: AddReviewInput!): Review!
}

input CreateProductInput {
    name: String!
    description: String
    salePrice: Float!
}

input AddReviewInput {
    productId: ID!
    reviewerName: String!
    comment: String!
    rating: Int!
}
```

## API Operations

### Queries

1. Get Product by ID
```graphql
query GetProduct($id: ID!) {
  productById(id: $id) {
    id
    name
    description
    salePrice
    reviews {
      id
      reviewerName
      comment
      rating
    }
  }
}
```

2. Get All Products
```graphql
query {
  allProducts {
    id
    name
    description
    salePrice
    reviews {
      reviewerName
      rating
      comment
    }
  }
}
```

### Mutations

1. Create Product
```graphql
mutation CreateProduct($input: CreateProductInput!) {
  createProduct(input: $input) {
    id
    name
    description
    salePrice
  }
}

# Variables
{
  "input": {
    "name": "Gaming Laptop",
    "description": "High-performance gaming laptop with RTX 4080",
    "salePrice": 1999.99
  }
}
```

2. Add Product Review
```graphql
mutation AddReview($input: AddReviewInput!) {
  addReview(input: $input) {
    id
    reviewerName
    comment
    rating
    product {
      id
      name
    }
  }
}

# Variables
{
  "input": {
    "productId": "1",
    "reviewerName": "John Doe",
    "comment": "Excellent gaming performance and build quality",
    "rating": 5
  }
}
```

## Setup and Running

1. Clone the repository:
   ```bash
   git clone https://github.com/2013techsmarts/Code_Samples.git
   ```

2. Navigate to the project directory:
   ```bash
   cd Code_Samples/Spring-GraphQL
   ```

3. Build the project:
   ```bash
   ./mvnw clean install
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Access the GraphQL endpoints:
   - GraphQL endpoint: http://localhost:8180/graphql
   - GraphiQL interface: http://localhost:8180/graphiql
   - H2 Console: http://localhost:8180/h2-console


## Testing with Bruno Collection

A Bruno collection is provided in the `graphql-collection` directory for testing the API. The collection includes:

1. Product Operations:
   - Add Product
   - Get Product by ID
   - Get All Products

2. Review Operations:
   - Add Product Review

To use the collection:
1. Import `spring-graphql-collection.json` into Bruno
2. Execute the requests in sequence (create product first, then add reviews) 