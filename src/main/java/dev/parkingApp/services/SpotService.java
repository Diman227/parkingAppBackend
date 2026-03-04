package dev.parkingApp.services;

import dev.parkingApp.dtos.kafka.SpotMessage;
import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.response.ImageResponse;
import dev.parkingApp.dtos.response.SpotResponse;
import dev.parkingApp.entities.SpotEntity;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.exceptions.SpotNotFoundException;
import dev.parkingApp.mappers.ImageMapper;
import dev.parkingApp.mappers.SpotMapper;
import dev.parkingApp.repositories.ImageRepository;
import dev.parkingApp.repositories.ReviewRepository;
import dev.parkingApp.repositories.SpotRepository;
import dev.parkingApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;
    private final ImageRepository imageRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    private final ImageAttachmentService imageAttachmentService;

    private final ImageMapper imageMapper;
    private final SpotMapper spotMapper;

    @Transactional
    public SpotResponse createSpotFromRequest(SpotRequest spotDTO, List<MultipartFile> images) {

        SpotEntity spot = spotMapper.createSpotEntityFromSpotRequest(spotDTO);

        //spot.setRate(calculateSpotRating(spot.getId()));

        SpotResponse response = spotMapper.toSpotResponse(spotRepository.save(spot));

        log.info("Saved spot from request is - {}", spot.toString());

        if(spotDTO.getImages() != null && !spotDTO.getImages().isEmpty()) {
            // todo
            List<ImageResponse> imagesResponse = imageAttachmentService.attachRequestImagesToSpot(
                    response.getId(),
                    images);
            imageRepository.saveAll(imageMapper.toListImageEntities(imagesResponse));
        }

        return response;
    }

    @Transactional
    public void createSpotFromMessage(SpotMessage spotMessage) {

        SpotEntity spot = spotMapper.createSpotEntityFromSpotMessage(spotMessage);

        Optional<UserEntity> spotOwner = userRepository.getExternalUser(spot.getExternalOwnerId());
        spotOwner.ifPresent(userEntity -> spot.setOwnerId(userEntity.getId()));

        spotRepository.save(spot);

        log.info("Saved spot from message is - {}", spot.toString());

        if(spotMessage.getImages() != null && !spotMessage.getImages().isEmpty()) {
            // todo
            List<ImageResponse> images = imageAttachmentService.attachMessageImagesToSpot(
                    spot.getId(),
                    spotMessage.getImages());
            imageRepository.saveAll(imageMapper.toListImageEntities(images));
        }
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
    private BigDecimal calculateSpotRating(Long spotId) {
        return reviewRepository.calculateSpotRating(spotId);
    }

    public void updateUnownedSpots(Long userId, Long externalUserId) {
        if(spotRepository.areUnownedSpotsWithoutOwnerExist(externalUserId)) {
            log.info("Найдены споты без владельца с externalOwnerId = {}", externalUserId);
            spotRepository.updateUnownedSpotsWithoutOwner(userId, externalUserId);
            log.info("Владелец спотов успешно найден и присвоен записям, ownerId = {}", userId);
            return;
        }
        log.info("Бесхозных спотов не найдено!");
    }
}
