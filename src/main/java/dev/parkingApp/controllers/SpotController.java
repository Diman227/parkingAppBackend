package dev.parkingApp.controllers;

import dev.parkingApp.dtos.SpotDTO;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "*api/base/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @PostMapping(value = "spots")
    public SpotDTO addSpot(@RequestBody SpotDTO spotDTO) {
        return spotService.addSpot(spotDTO);
    }

    @PutMapping(value = "spots")
    public SpotDTO updateSpot(@RequestBody SpotDTO spotDTO) {
        return spotService.updateSpot(spotDTO);
    }

    @DeleteMapping(value = "spots/{id}")
    public Long deleteSpot(@PathVariable("id") Long spotId) {
        return spotService.deleteSpot(spotId);
    }

    @GetMapping(value = "{userId}/spots")
    public List<SpotDTO> getUserOwnedSpots(@PathVariable("userId") Long userId) {
        return spotService.getUserOwnedSpots(userId);
    }
}
