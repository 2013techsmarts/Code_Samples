package co.smarttechie.controller;

import co.smarttechie.repository.ProductRepository;
import co.smarttechie.repository.ReviewRepository;
import co.smarttechie.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<co.smarttechie.model.Product> createProduct(@RequestBody co.smarttechie.model.Product product) {
        co.smarttechie.model.Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<co.smarttechie.model.Product> getProduct(@PathVariable Long id) {
        co.smarttechie.model.Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/{productId}/reviews")
    public co.smarttechie.model.Product addReviewToProduct(
            @PathVariable Long productId,
            @RequestBody co.smarttechie.model.Review reviewDTO) {

        return productService.addReviewToProduct(productId, reviewDTO);

    }

} 