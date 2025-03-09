package co.smarttechie.service;

import org.springframework.stereotype.Service;

import co.smarttechie.model.ProductRecord;
import co.smarttechie.entity.Product;
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
            Product product = Product.builder()
                .name(request.getName())
                .salePrice(request.getSalePrice())
                .build();

            Product savedProduct = productRepository.save(product);
            ProductRecord productRecord = ProductRecord.fromEntity(savedProduct);

            co.smarttechie.grpc.Product grpcProduct = co.smarttechie.grpc.Product.newBuilder()
                .setId(productRecord.id())
                .setName(productRecord.name())
                .setSalePrice(productRecord.salePrice())
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
            Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

            ProductRecord productRecord = ProductRecord.fromEntity(product);

            co.smarttechie.grpc.Product grpcProduct = co.smarttechie.grpc.Product.newBuilder()
                .setId(productRecord.id())
                .setName(productRecord.name())
                .setSalePrice(productRecord.salePrice())
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