package dev.parkingApp.services;

import dev.parkingApp.dtos.request.ReviewRequest;
import dev.parkingApp.dtos.response.ReviewResponse;
import dev.parkingApp.entities.ReviewEntity;
import dev.parkingApp.entities.SpotEntity;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.exceptions.ReviewNotFoundException;
import dev.parkingApp.exceptions.UserHaveNotPermissionException;
import dev.parkingApp.mappers.ReviewMapper;
import dev.parkingApp.repositories.BookingRepository;
import dev.parkingApp.repositories.ReviewRepository;
import dev.parkingApp.repositories.SpotRepository;
import dev.parkingApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final SpotRepository spotRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    private final ReviewMapper reviewMapper;

    public ReviewResponse addReview(ReviewRequest reviewDTO) {

        // todo add images
        if(!bookingRepository.hadUserBookingOfSpot(reviewDTO.getSpotId(), reviewDTO.getAuthorId(), LocalDateTime.now())) {
            throw new UserHaveNotPermissionException("Пользователь не может оставить отзыв на место с id " + reviewDTO.getSpotId());
        }
        ReviewEntity review = reviewMapper.toReviewEntity(reviewDTO);

        SpotEntity spot = spotRepository.getReferenceById(reviewDTO.getSpotId());
        UserEntity author = userRepository.getReferenceById(reviewDTO.getAuthorId());

        review.setSpot(spot);
        review.setAuthor(author);

        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }

    // todo я напрямую получаю список, но не обработываю исключение на существование места
    public List<ReviewResponse> getSpotReviews(Long spotId) {
        return reviewMapper.toListReviewResponses(reviewRepository.getSpotReviews(spotId));
    }

    public ReviewResponse updateReview(ReviewRequest reviewDTO) {

        ReviewEntity review = reviewRepository.findById(reviewDTO.getId()).orElseThrow(
                () -> new ReviewNotFoundException("Review with id - " + reviewDTO.getId() + "- wasn't found"));

        review.setRate(reviewDTO.getRate());
        review.setMessage(reviewDTO.getMessage());

        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }
}
