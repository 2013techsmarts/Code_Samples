package co.smarttechie.resolver;

import co.smarttechie.entity.Product;
import co.smarttechie.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductResolver {
    private final ProductService productService;

    @QueryMapping
    public Product productById(@Argument Long id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @QueryMapping
    public List<Product> allProducts() {
        return productService.getAllProducts();
    }

    @MutationMapping
    public Product createProduct(@Argument CreateProductInput input) {
        return productService.createProduct(
                input.name(),
                input.description(),
                input.salePrice()
        );
    }

    record CreateProductInput(String name, String description, Double salePrice) {}
} 