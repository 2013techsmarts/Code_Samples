package co.smarttechie.model;

public record ProductRecord(Long id, String name, Double salePrice) {
    public static ProductRecord fromEntity(co.smarttechie.entity.Product product) {
        return new ProductRecord(
            product.getId(),
            product.getName(),
            product.getSalePrice()
        );
    }

    public co.smarttechie.entity.Product toEntity() {
        return co.smarttechie.entity.Product.builder()
            .id(id)
            .name(name)
            .salePrice(salePrice)
            .build();
    }
} 