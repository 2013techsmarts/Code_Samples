package co.smarttechie.resolver;

import co.smarttechie.entity.Review;
import co.smarttechie.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReviewResolver {
    private final ReviewService reviewService;

    @MutationMapping
    public Review addReview(@Argument AddReviewInput input) {
        return reviewService.addReview(
                input.productId(),
                input.reviewerName(),
                input.content(),
                input.rating()
        );
    }

    record AddReviewInput(Long productId, String reviewerName, String content, Integer rating) {}
} 