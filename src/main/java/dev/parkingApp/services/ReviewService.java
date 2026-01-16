package dev.parkingApp.services;

import dev.parkingApp.dtos.ReviewDTO;
import dev.parkingApp.mappers.ReviewMapper;
import dev.parkingApp.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        return reviewDTO;
        //todo insert nativeQuery
    }
}
