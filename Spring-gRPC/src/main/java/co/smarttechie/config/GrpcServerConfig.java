package co.smarttechie.config;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import co.smarttechie.service.ProductService;
import co.smarttechie.grpc.ProductServiceGrpc;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@Component
public class GrpcServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(GrpcServerConfig.class);
    private Server server;
    private final ProductService productService;

    @Autowired
    public GrpcServerConfig(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void startServer() throws Exception {
        server = ServerBuilder.forPort(9090)
                .addService((ProductServiceGrpc.ProductServiceImplBase) productService)
                .build()
                .start();
        
        logger.info("gRPC Server started on port 9090");

        // Add shutdown hook to ensure graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down gRPC server...");
            if (server != null) {
                server.shutdown();
            }
            logger.info("gRPC server shut down successfully");
        }));

        // Keep the server running
        server.awaitTermination();
    }

    @PreDestroy
    public void stopServer() {
        if (server != null) {
            server.shutdown();
        }
    }
} 