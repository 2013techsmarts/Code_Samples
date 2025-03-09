package co.smarttechie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.annotation.ComponentScan;

import co.smarttechie.grpc.CreateProductRequest;
import co.smarttechie.grpc.CreateProductResponse;
import co.smarttechie.grpc.Product;
import co.smarttechie.grpc.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
@ComponentScan(basePackages = {
    "co.smarttechie.config",
    "co.smarttechie.service",
    "co.smarttechie.repository"
})
public class SpringGRpcApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringGRpcApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringGRpcApplication.class, args);
	}

	@EventListener(ApplicationStartedEvent.class)
	public void testGrpcServer() {
		logger.info("Testing gRPC server with client calls...");
		
		// Create a channel
		ManagedChannel channel = ManagedChannelBuilder
			.forAddress("localhost", 9090)
			.usePlaintext() // Disable TLS for testing
			.build();

		try {
			// Create a blocking stub
			ProductServiceGrpc.ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);

			// Create a product request
			CreateProductRequest request = CreateProductRequest.newBuilder()
				.setName("iPhone 15 Pro")
				.setSalePrice(999.99)
				.build();

			// Call the service and get response
			CreateProductResponse response = stub.createProduct(request);

			// Print the response
			Product createdProduct = response.getProduct();
			logger.info("Product created successfully!");
			logger.info("Product ID: {}", createdProduct.getId());
			logger.info("Product Name: {}", createdProduct.getName());
			logger.info("Product Price: ${}", createdProduct.getSalePrice());
			logger.info("Server Message: {}", response.getMessage());

		} catch (Exception e) {
			logger.error("Error creating product: {}", e.getMessage(), e);
		} finally {
			// Shutdown the channel
			channel.shutdown();
		}
	}
}
