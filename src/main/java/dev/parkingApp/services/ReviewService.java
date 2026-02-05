package dev.parkingApp.services;

import dev.parkingApp.dtos.request.ReviewRequest;
import dev.parkingApp.dtos.response.ReviewResponse;
import dev.parkingApp.entities.ImageEntity;
import dev.parkingApp.entities.ReviewEntity;
import dev.parkingApp.entities.SpotEntity;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.exceptions.ReviewNotFoundException;
import dev.parkingApp.exceptions.SpotNotFoundException;
import dev.parkingApp.exceptions.UserHaveNotPermissionException;
import dev.parkingApp.mappers.ImageMapper;
import dev.parkingApp.mappers.ReviewMapper;
import dev.parkingApp.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final SpotRepository spotRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final ImageRepository imageRepository;

    private final ImageAttachmentService imageAttachmentService;

    private final ReviewMapper reviewMapper;
    private final ImageMapper imageMapper;

    @Transactional
    public ReviewResponse addReview(ReviewRequest reviewDTO) {

        // todo add images
        if(!bookingRepository.hadUserBookingOfSpot(reviewDTO.getSpotId(), reviewDTO.getAuthorId(), LocalDateTime.now())) {
            throw new UserHaveNotPermissionException("User can't post review for spot with id - " + reviewDTO.getSpotId());
        }

        ReviewEntity review = reviewMapper.toReviewEntity(reviewDTO);

        SpotEntity spot = spotRepository.getReferenceById(reviewDTO.getSpotId());
        UserEntity author = userRepository.getReferenceById(reviewDTO.getAuthorId());

        review.setSpot(spot);
        review.setAuthor(author);

        ReviewResponse response = reviewMapper.toReviewResponse(reviewRepository.save(review));

        if(!reviewDTO.getImages().isEmpty()) {
            imageRepository.saveAll(imageMapper.toListImageEntities(imageAttachmentService.attachImagesToReview(review.getId(), reviewDTO.getImages())));
        }

        return response;
    }

    public List<ReviewResponse> getSpotReviews(Long spotId) {

        if(!spotRepository.existsById(spotId)) throw new SpotNotFoundException("Spot wasn't found with id - " + spotId);
        List<ReviewEntity> reviews = reviewRepository.getSpotReviews(spotId);
        return reviewMapper.toListReviewResponses(reviews);
    }

    public ReviewResponse updateReview(ReviewRequest reviewDTO) {

        ReviewEntity review = reviewRepository.findById(reviewDTO.getId()).orElseThrow(
                () -> new ReviewNotFoundException("Review with id - " + reviewDTO.getId() + "- wasn't found"));

        review.setRate(reviewDTO.getRate());
        review.setMessage(reviewDTO.getMessage());

        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }

    public List<ReviewResponse> getAllReviews() {
        return reviewMapper.toListReviewResponses(reviewRepository.getAllReviews());
    }
}
