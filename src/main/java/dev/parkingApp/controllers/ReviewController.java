package dev.parkingApp.controllers;

import dev.parkingApp.dtos.ReviewDTO;
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
    public List<ReviewDTO> getSpotReviews(@PathVariable("spotId") Long spotId) {
        return reviewService.getSpotReviews(spotId);
    }

    @PostMapping
    @PreAuthorize("#reviewDTO.authorId == authentication.principal.userId")
    public ReviewDTO createReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.addReview(reviewDTO);
    }

    @PutMapping
    @PreAuthorize("#reviewDTO.authorId == authentication.principal.userId")
    public ReviewDTO updateReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.updateReview(reviewDTO);
    }
}
