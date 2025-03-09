package co.smarttechie.service;

import org.springframework.stereotype.Service;

import co.smarttechie.grpc.CreateProductRequest;
import co.smarttechie.grpc.CreateProductResponse;
import co.smarttechie.grpc.GetProductRequest;
import co.smarttechie.grpc.GetProductResponse;
import co.smarttechie.grpc.ProductServiceGrpc.ProductServiceImplBase;
import co.smarttechie.repository.ProductRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService extends ProductServiceImplBase {
    
    private final ProductRepository productRepository;

    @Override
    public void createProduct(CreateProductRequest request, StreamObserver<CreateProductResponse> responseObserver) {
        try {
            co.smarttechie.model.Product product = new co.smarttechie.model.Product(
                null,
                request.getName(),
                request.getSalePrice(),
                null
            );

            co.smarttechie.entity.Product savedEntity = productRepository.save(product.toEntity());
            co.smarttechie.model.Product savedProduct = co.smarttechie.model.Product.fromEntity(savedEntity);

            co.smarttechie.grpc.Product grpcProduct = co.smarttechie.grpc.Product.newBuilder()
                .setId(savedProduct.id())
                .setName(savedProduct.name())
                .setSalePrice(savedProduct.salePrice())
                .build();

            CreateProductResponse response = CreateProductResponse.newBuilder()
                .setProduct(grpcProduct)
                .setMessage("Product created successfully")
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                .withDescription("Error creating product: " + e.getMessage())
                .asRuntimeException());
        }
    }

    @Override
    public void getProduct(GetProductRequest request, StreamObserver<GetProductResponse> responseObserver) {
        try {
            co.smarttechie.entity.Product entity = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

            co.smarttechie.model.Product product = co.smarttechie.model.Product.fromEntity(entity);

            co.smarttechie.grpc.Product grpcProduct = co.smarttechie.grpc.Product.newBuilder()
                .setId(product.id())
                .setName(product.name())
                .setSalePrice(product.salePrice())
                .build();

            GetProductResponse response = GetProductResponse.newBuilder()
                .setProduct(grpcProduct)
                .setMessage("Product retrieved successfully")
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.NOT_FOUND
                .withDescription("Error retrieving product: " + e.getMessage())
                .asRuntimeException());
        }
    }
} 