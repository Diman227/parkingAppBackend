package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.ReviewRequest;
import dev.parkingApp.dtos.response.ReviewResponse;
import dev.parkingApp.services.ReviewService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "*api/base/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    // todo ???
    @GetMapping("/{spotId}")
    public List<ReviewResponse> getSpotReviews(@PathVariable("spotId") @NotNull @Positive Long spotId) {
        return reviewService.getSpotReviews(spotId);
    }

    @PostMapping
    @PreAuthorize("#reviewDTO.authorId == authentication.principal.userId")
    public ReviewResponse createReview(@RequestBody @Validated(ReviewRequest.Create.class) ReviewRequest reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }

    @PutMapping
    @PreAuthorize("#reviewDTO.authorId == authentication.principal.userId")
    public ReviewResponse updateReview(@RequestBody @Validated(ReviewRequest.Update.class) ReviewRequest reviewDTO) {
        return reviewService.updateReview(reviewDTO);
    }

    @GetMapping
    public List<ReviewResponse> getAllReviews() {
        return reviewService.getAllReviews();
    }
}
