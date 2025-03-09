const grpc = require('@grpc/grpc-js');
const protoLoader = require('@grpc/proto-loader');
const path = require('path');

// Load the proto file
const PROTO_PATH = path.join(__dirname, 'proto/product.proto');
const packageDefinition = protoLoader.loadSync(PROTO_PATH, {
    keepCase: true,
    longs: String,
    enums: String,
    defaults: true,
    oneofs: true
});

// Load the package definition
const productProto = grpc.loadPackageDefinition(packageDefinition).product;

// Create a gRPC client
const client = new productProto.ProductService(
    'localhost:9090',
    grpc.credentials.createInsecure()
);

// Function to create a product
function createProduct(name, salePrice) {
    return new Promise((resolve, reject) => {
        client.createProduct({
            name: name,
            salePrice: salePrice
        }, (error, response) => {
            if (error) {
                reject(error);
            } else {
                resolve(response);
            }
        });
    });
}

// Function to add a review to a product
function addReview(productId, comment, rating, reviewerName) {
    return new Promise((resolve, reject) => {
        client.addReview({
            product_id: productId,
            comment: comment,
            rating: rating,
            reviewerName: reviewerName
        }, (error, response) => {
            if (error) {
                reject(error);
            } else {
                resolve(response);
            }
        });
    });
}

// Function to get product details
function getProduct(productId) {
    return new Promise((resolve, reject) => {
        client.getProduct({
            product_id: productId
        }, (error, response) => {
            if (error) {
                reject(error);
            } else {
                resolve(response);
            }
        });
    });
}

// Example usage
async function main() {
    try {
        // Create a product
        console.log('Creating a product...');
        const productResponse = await createProduct('iPhone 15 Pro', 999.99);
        console.log('Product created:', productResponse);
        
        const productId = productResponse.product.id;
        
        // Add a review for the product
        console.log('\nAdding a review...');
        const reviewResponse = await addReview(
            productId,
            'Great product, amazing camera!',
            5,
            'John Doe'
        );
        console.log('Review added:', reviewResponse);
        
        // Get the product details with the review
        console.log('\nGetting product details...');
        const productDetails = await getProduct(productId);
        console.log('Product details:', JSON.stringify(productDetails, null, 2));
        
    } catch (error) {
        console.error('Error:', error);
    } finally {
        // Close the client connection
        client.close();
    }
}

// Run the example
main(); 