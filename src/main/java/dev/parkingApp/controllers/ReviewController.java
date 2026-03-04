package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.ReviewRequest;
import dev.parkingApp.dtos.response.ReviewResponse;
import dev.parkingApp.services.ReviewService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "*api/base/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{spotId}")
    public ResponseEntity<List<ReviewResponse>> getSpotReviews(
            @PathVariable("spotId") @NotNull @Positive Long spotId) {
        return new ResponseEntity<>(reviewService.getSpotReviews(spotId), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("#reviewDTO.authorId == authentication.principal.userId")
    public ResponseEntity<ReviewResponse> createReview(
            @RequestPart @Validated(ReviewRequest.Create.class) ReviewRequest reviewDTO,
            @RequestPart("images") List<MultipartFile> images) {
        return new ResponseEntity<>(reviewService.createReview(reviewDTO, images), HttpStatus.CREATED);
    }

    @PutMapping("/{reviewId}")
    @PreAuthorize("#reviewDTO.authorId == authentication.principal.userId")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable("reviewId") Long reviewId,
            @RequestBody @Validated(ReviewRequest.Update.class) ReviewRequest reviewDTO) {
        return new ResponseEntity<>(reviewService.updateReview(reviewId, reviewDTO), HttpStatus.OK);
    }
}
