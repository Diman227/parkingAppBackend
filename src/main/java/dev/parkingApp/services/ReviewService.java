package dev.parkingApp.services;

import dev.parkingApp.dtos.ReviewDTO;
import dev.parkingApp.entities.ReviewEntity;
import dev.parkingApp.entities.SpotEntity;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.exceptions.ReviewNotFoundException;
import dev.parkingApp.mappers.ReviewMapper;
import dev.parkingApp.repositories.ReviewRepository;
import dev.parkingApp.repositories.SpotRepository;
import dev.parkingApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final SpotRepository spotRepository;
    private final UserRepository userRepository;

    private final ReviewMapper reviewMapper;

    public ReviewDTO addReview(ReviewDTO reviewDTO) {

        // todo add images
        // todo какая-то проверка на то, была ли у пользователя аренда этого места, чтоб он мог оставить отзыв
        ReviewEntity review = reviewMapper.toReviewEntity(reviewDTO);

        SpotEntity spot = spotRepository.getReferenceById(reviewDTO.getSpotId());
        UserEntity author = userRepository.getReferenceById(reviewDTO.getAuthorId());

        review.setSpot(spot);
        review.setAuthor(author);

        return reviewMapper.toReviewDTO(reviewRepository.save(review));
    }

    // todo я напрямую получаю список, но не обработываю исключение на существование места
    public List<ReviewDTO> getSpotReviews(Long spotId) {
        return reviewMapper.toListReviewDTOs(reviewRepository.getSpotReviews(spotId));
    }

    public ReviewDTO updateReview(ReviewDTO reviewDTO) {

        ReviewEntity review = reviewRepository.findById(reviewDTO.getId()).orElseThrow(() -> new ReviewNotFoundException("Review with id - " + reviewDTO.getId() + "- wasn't found"));

        review.setRate(reviewDTO.getRate());
        review.setMessage(reviewDTO.getMessage());

        return reviewMapper.toReviewDTO(reviewRepository.save(review));
    }
}
