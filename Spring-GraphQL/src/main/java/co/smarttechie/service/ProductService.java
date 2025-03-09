package co.smarttechie.service;

import co.smarttechie.entity.Product;
import co.smarttechie.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(String name, String description, Double salePrice) {
        Product product = Product.builder()
                .name(name)
                .salePrice(salePrice)
                .build();
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
} 