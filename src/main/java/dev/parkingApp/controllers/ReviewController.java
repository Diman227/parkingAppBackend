package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.ReviewRequest;
import dev.parkingApp.dtos.response.ReviewResponse;
import dev.parkingApp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "*/api/base/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping(value = "/{spotId}")
    public List<ReviewResponse> getSpotReviews(@PathVariable("spotId") Long spotId) {
        return reviewService.getSpotReviews(spotId);
    }

    @PostMapping
    @PreAuthorize("#reviewDTO.authorId == authentication.principal.userId")
    public ReviewResponse createReview(@RequestBody ReviewRequest reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }

    @PutMapping
    @PreAuthorize("#reviewDTO.authorId == authentication.principal.userId")
    public ReviewResponse updateReview(@RequestBody ReviewRequest reviewDTO) {
        return reviewService.updateReview(reviewDTO);
    }
}
