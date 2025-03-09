package co.smarttechie.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.smarttechie.grpc.AddReviewRequest;
import co.smarttechie.grpc.AddReviewResponse;
import co.smarttechie.grpc.CreateProductRequest;
import co.smarttechie.grpc.CreateProductResponse;
import co.smarttechie.grpc.GetProductRequest;
import co.smarttechie.grpc.GetProductResponse;
import co.smarttechie.grpc.GetProductReviewsRequest;
import co.smarttechie.grpc.GetProductReviewsResponse;
import co.smarttechie.grpc.ProductServiceGrpc.ProductServiceImplBase;
import co.smarttechie.repository.ProductRepository;
import co.smarttechie.repository.ReviewRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService extends ProductServiceImplBase {
    
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

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
            
            // Initialize the reviews collection
            entity.getReviews().size();

            co.smarttechie.model.Product product = co.smarttechie.model.Product.fromEntity(entity);

            var reviewsBuilder = entity.getReviews().stream()
                .map(review -> co.smarttechie.grpc.Review.newBuilder()
                    .setId(review.getId())
                    .setComment(review.getComment())
                    .setRating(review.getRating())
                    .setReviewerName(review.getReviewerName())
                    .build())
                .toList();

            co.smarttechie.grpc.Product grpcProduct = co.smarttechie.grpc.Product.newBuilder()
                .setId(product.id())
                .setName(product.name())
                .setSalePrice(product.salePrice())
                .addAllReviews(reviewsBuilder)
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

    @Override
    public void addReview(AddReviewRequest request, StreamObserver<AddReviewResponse> responseObserver) {
        try {
            co.smarttechie.entity.Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

            co.smarttechie.entity.Review review = new co.smarttechie.entity.Review();
            review.setComment(request.getComment());
            review.setRating(request.getRating());
            review.setReviewerName(request.getReviewerName());
            review.setProduct(product);

            co.smarttechie.entity.Review savedReview = reviewRepository.save(review);
            
            // Refresh the product entity to ensure reviews are up to date
            entityManager.refresh(product);

            co.smarttechie.grpc.Review grpcReview = co.smarttechie.grpc.Review.newBuilder()
                .setId(savedReview.getId())
                .setComment(savedReview.getComment())
                .setRating(savedReview.getRating())
                .setReviewerName(savedReview.getReviewerName())
                .build();

            AddReviewResponse response = AddReviewResponse.newBuilder()
                .setReview(grpcReview)
                .setMessage("Review added successfully")
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                .withDescription("Error adding review: " + e.getMessage())
                .asRuntimeException());
        }
    }

    @Override
    public void getProductReviews(GetProductReviewsRequest request, StreamObserver<GetProductReviewsResponse> responseObserver) {
        try {
            co.smarttechie.entity.Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
            
            // Initialize the reviews collection
            product.getReviews().size();

            var reviews = product.getReviews().stream()
                .map(review -> co.smarttechie.grpc.Review.newBuilder()
                    .setId(review.getId())
                    .setComment(review.getComment())
                    .setRating(review.getRating())
                    .setReviewerName(review.getReviewerName())
                    .build())
                .toList();

            GetProductReviewsResponse response = GetProductReviewsResponse.newBuilder()
                .addAllReviews(reviews)
                .setMessage("Reviews retrieved successfully")
                .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.NOT_FOUND
                .withDescription("Error retrieving reviews: " + e.getMessage())
                .asRuntimeException());
        }
    }
} 