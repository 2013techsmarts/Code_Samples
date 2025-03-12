package co.smarttechie.model;

import java.time.LocalDateTime;

public record Board(
    Long id,
    String title,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static Board fromEntity(co.smarttechie.entity.Board entity) {
        return new Board(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
} 