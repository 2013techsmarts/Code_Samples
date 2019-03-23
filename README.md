# Code Samples
This repository contains sample projects created while exploring new technologies
# Follow below steps to setup rest-demo project
1. rest-demo is a gradle project. Import it to your favorite IDE
2. Run Springboot project. This project uses H2 Database.
3. Go to http://localhost:8080/h2/ and run initiate_db.SQL file to have product data.
4. Make a call to http://localhost:8080/product/1 to get product details response.
# Follow below steps to setup graphql-demo project
1. graphql-demo is a gradle project. Import it to your favorite IDE
2. Run Springboot project. This project uses H2 Database.
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
