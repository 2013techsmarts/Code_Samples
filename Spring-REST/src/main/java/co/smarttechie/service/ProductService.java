package co.smarttechie.service;

import co.smarttechie.entity.Product;
import co.smarttechie.entity.Review;
import co.smarttechie.repository.ProductRepository;
import co.smarttechie.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public ProductService(ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public co.smarttechie.model.Product createProduct(co.smarttechie.model.Product productDTO) {
        Product product = productDTO.toEntity();
        Product savedProduct = productRepository.save(product);
        return co.smarttechie.model.Product.fromEntity(savedProduct);
    }

    public co.smarttechie.model.Product getProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return co.smarttechie.model.Product.fromEntity(product);
    }

    public co.smarttechie.model.Product addReviewToProduct(Long productId, co.smarttechie.model.Review reviewDTO) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        
        Review review = reviewDTO.toEntity();
        product.addReview(review);
        Product updatedProduct = productRepository.save(product);
        
        return co.smarttechie.model.Product.fromEntity(updatedProduct);
    }

} 