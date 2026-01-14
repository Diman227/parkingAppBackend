package dev.parkingApp.services;

import dev.parkingApp.mappers.ReviewMapper;
import dev.parkingApp.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
}
