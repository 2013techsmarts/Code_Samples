package co.smarttechie.model;

import java.util.List;

public record Product(
    Long id,
    String name,
    Double salePrice,
    List<Review> reviews
) {
    public static Product fromEntity(co.smarttechie.entity.Product product) {
        List<Review> reviewRecords = product.getReviews().stream()
            .map(Review::fromEntity)
            .toList();

        return new Product(
            product.getId(),
            product.getName(),
            product.getSalePrice(),
            reviewRecords
        );
    }

    public co.smarttechie.entity.Product toEntity() {
        co.smarttechie.entity.Product product = co.smarttechie.entity.Product.builder()
            .name(name)
            .salePrice(salePrice)
            .build();

        if (reviews != null) {
            reviews.stream()
                .map(Review::toEntity)
                .forEach(product::addReview);
        }

        return product;
    }
} 