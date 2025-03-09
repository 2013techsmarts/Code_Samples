package co.smarttechie.service;

import co.smarttechie.entity.Product;
import co.smarttechie.entity.Review;
import co.smarttechie.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    public Review addReview(Long productId, String reviewerName, String comment, Integer rating) {
        Product product = productService.getProductById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        Review review = Review.builder()
                .reviewerName(reviewerName)
                .comment(comment)
                .rating(rating)
                .product(product)
                .build();

        return reviewRepository.save(review);
    }
} 