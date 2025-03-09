package co.smarttechie.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.smarttechie.service.ProductService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PreDestroy;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class GrpcServerConfig {

    @Value("${grpc.server.port:9090}")
    private int grpcPort;

    private Server server;
    private final ProductService productService;

    public GrpcServerConfig(ProductService productService) {
        this.productService = productService;
    }

    @Bean
    public Server grpcServer() throws IOException {
        server = ServerBuilder.forPort(grpcPort)
            .addService(productService)
            .build()
            .start();
        
        return server;
    }

    @PreDestroy
    public void stopGrpcServer() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }
} 