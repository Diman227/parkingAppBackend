package dev.parkingApp.services;

import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.response.ImageResponse;
import dev.parkingApp.dtos.response.SpotResponse;
import dev.parkingApp.entities.SpotEntity;
import dev.parkingApp.exceptions.SpotNotFoundException;
import dev.parkingApp.mappers.ImageMapper;
import dev.parkingApp.mappers.SpotMapper;
import dev.parkingApp.repositories.ImageRepository;
import dev.parkingApp.repositories.ReviewRepository;
import dev.parkingApp.repositories.SpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;
    private final ImageRepository imageRepository;
    private final ReviewRepository reviewRepository;

    private final ImageAttachmentService imageAttachmentService;

    private final ImageMapper imageMapper;
    private final SpotMapper spotMapper;

    @Transactional
    public SpotResponse createSpot(SpotRequest spotDTO) {

        SpotEntity spot = spotMapper.createSpotEntity(spotDTO);

        //spot.setRate(calculateSpotRating(spot.getId()));

        SpotResponse response = spotMapper.toSpotResponse(spotRepository.save(spot));

        log.info("Saved spot is - {}", spot.toString());

        if(spotDTO.getImages() != null && !spotDTO.getImages().isEmpty()) {
            // todo
            List<ImageResponse> images = imageAttachmentService.attachImagesToSpot(
                    response.getId(),
                    spotDTO.getImages());
            imageRepository.saveAll(imageMapper.toListImageEntities(images));
        }

        return response;
    }

    public SpotResponse updateSpot(Long spotId, SpotRequest spotDTO) {

        SpotEntity spot = spotRepository.findById(spotId).orElseThrow(
                () -> new SpotNotFoundException("Spot with id - " + spotId + " - wasn't found!"));

        spotRepository.save(spotMapper.updateSpotEntity(spot, spotDTO));

        return spotMapper.toSpotResponse(spot);
    }

    public Long deleteSpot(Long spotId) {
        spotRepository.deleteById(spotId);
        return spotId;
    }

    public List<SpotResponse> getUserOwnedSpots(Long userId) {
        List<SpotEntity> spot = spotRepository.getUserOwnedSpots(userId);
        return spotMapper.toListSpotResponses(spot);
    }

    public List<SpotResponse> getAllSpots() {
        return spotMapper.toListSpotResponses(spotRepository.getAllSpots());
    }

    // todo
    public BigDecimal calculateSpotRating(Long spotId) {
        return reviewRepository.calculateSpotRating(spotId);
    }
}
