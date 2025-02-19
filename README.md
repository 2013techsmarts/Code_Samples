# Code Samples
This repository contains sample projects created while exploring new technologies
# Follow below steps to setup rest-demo project
1. rest-demo is a gradle project. Import it to your favorite IDE
2. Run Spring Boot project. This project uses H2 Database.
3. Go to http://localhost:8080/h2/ and run initiate_db.SQL file to have product data.
4. Make a call to http://localhost:8080/product/1 to get product details response.
# Follow below steps to setup graphql-demo project
1. graphql-demo is a gradle project. Import it to your favorite IDE
2. Run Spring Boot project. This project uses H2 Database.
3. Go to http://localhost:8080/h2/ and run initiate_db.SQL file to have product data.
4. The graphql-demo has graphiQL added. So we can play GraphQL queries with GUI. Make a call to http://localhost:8080/graphiql
5. Send below query to see product JSON response.

{
  product(id:"1") {
    id,
    title,
    shortDesc,
    listPrice,
    reviews {
      reviewTitle,
      reviewText
    }
  }
}

# Follow below steps to setup legacy-user-service and refactored-user-service project based on https://youtu.be/ebdAdkIuDEc

Title: Simplify Complex Validation Logic with the Rules Design Pattern | Spring Boot

Description:
Struggling with complex validation logic in your application? Not able to manage and write unit tests? The Rules Design Pattern will save you.  In this video, I will walk you through how to simplify and structure your validation logic using this powerful design pattern. We can achieve S.O.L.I.D principles when we apply Rules Design Pattern.

🔥 What You’ll Learn:
✅ What is the Rules Design Pattern?
✅ Why use it for validation?
✅ Look at the legacy code which has complex validation logic
✅ How to refactor the legacy code with Rules Design Pattern
