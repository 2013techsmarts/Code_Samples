package co.smarttechie.model;

import java.time.LocalDateTime;

public record Card(
    Long id,
    String title,
    String description,
    Integer position,
    Long boardId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static Card fromEntity(co.smarttechie.entity.Card entity) {
        return new Card(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getPosition(),
            entity.getBoard().getId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
} 