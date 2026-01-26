package dev.parkingApp.services;

import dev.parkingApp.dtos.request.SpotRequest;
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

    public SpotRequest addSpot(SpotRequest spotDTO) {

        SpotEntity spot = spotMapper.toSpotEntity(spotDTO);

        UserEntity user = userRepository.getReferenceById(spotDTO.getOwnerId());
        spot.setOwner(user);
        spot.setCreatedAt(LocalDateTime.now());
        return spotMapper.toSpotDTO(spotRepository.save(spot));
    }

    public SpotRequest updateSpot(SpotRequest spotDTO) {

        SpotEntity spot = spotRepository.findById(spotDTO.getId()).orElseThrow(() -> new SpotNotFoundException("Spot with id - " + spotDTO.getId() + " - wasn't found!"));

        spot.setDescription(spotDTO.getDescription());
        spot.setPrice(spotDTO.getPrice());

        return spotMapper.toSpotDTO(spotRepository.save(spot));
    }

    public Long deleteSpot(Long spotId) {
        spotRepository.deleteById(spotId);
        return spotId;
    }

    public List<SpotRequest> getUserOwnedSpots(Long userId) {
        return spotMapper.toListSpotDTOs(spotRepository.getUserOwnedSpots(userId));
    }

    public List<SpotRequest> getAllSpots() {
        return spotMapper.toListSpotDTOs(spotRepository.getAllSpots());
    }
}
