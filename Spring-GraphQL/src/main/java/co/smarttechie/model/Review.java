package co.smarttechie.model;

public record Review(
    Long id,
    String comment,
    Integer rating,
    String reviewerName
) {
    public static Review fromEntity(co.smarttechie.entity.Review review) {
        return new Review(
            review.getId(),
            review.getComment(),
            review.getRating(),
            review.getReviewerName()
        );
    }

    public co.smarttechie.entity.Review toEntity() {
        return co.smarttechie.entity.Review.builder()
            .id(id)
            .comment(comment)
            .rating(rating)
            .reviewerName(reviewerName)
            .build();
    }
} 