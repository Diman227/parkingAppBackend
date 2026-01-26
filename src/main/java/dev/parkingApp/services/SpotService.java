package dev.parkingApp.services;

import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.response.SpotResponse;
import dev.parkingApp.entities.SpotEntity;
import dev.parkingApp.entities.UserEntity;
import dev.parkingApp.exceptions.SpotNotFoundException;
import dev.parkingApp.mappers.SpotMapper;
import dev.parkingApp.repositories.SpotRepository;
import dev.parkingApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;
    private final UserRepository userRepository;

    public SpotResponse addSpot(SpotRequest spotDTO) {

        SpotEntity spot = spotMapper.toSpotEntity(spotDTO);

        UserEntity user = userRepository.getReferenceById(spotDTO.getOwnerId());
        spot.setOwner(user);
        spot.setCreatedAt(LocalDateTime.now());
        return spotMapper.toSpotResponse(spotRepository.save(spot));
    }

    public SpotResponse updateSpot(SpotRequest spotDTO) {

        SpotEntity spot = spotRepository.findById(spotDTO.getId()).orElseThrow(() -> new SpotNotFoundException("Spot with id - " + spotDTO.getId() + " - wasn't found!"));

        spot.setDescription(spotDTO.getDescription());
        spot.setPrice(spotDTO.getPrice());

        return spotMapper.toSpotResponse(spotRepository.save(spot));
    }

    public Long deleteSpot(Long spotId) {
        spotRepository.deleteById(spotId);
        return spotId;
    }

    public List<SpotResponse> getUserOwnedSpots(Long userId) {
        return spotMapper.toListSpotResponses(spotRepository.getUserOwnedSpots(userId));
    }

    public List<SpotResponse> getAllSpots() {
        return spotMapper.toListSpotResponses(spotRepository.getAllSpots());
    }
}
