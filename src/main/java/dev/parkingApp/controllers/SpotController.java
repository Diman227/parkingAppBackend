package dev.parkingApp.controllers;

import dev.parkingApp.dtos.SpotDTO;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "*api/base/spots", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @PostMapping
    @PreAuthorize("#spotDTO.ownerId == authentication.principal.userId")
    public SpotDTO addSpot(@RequestBody SpotDTO spotDTO) {
        return spotService.addSpot(spotDTO);
    }

    @PutMapping
    @PreAuthorize("#spotDTO.ownerId == authentication.principal.userId")
    public SpotDTO updateSpot(@RequestBody SpotDTO spotDTO) {
        return spotService.updateSpot(spotDTO);
    }

    // todo security?
    @DeleteMapping(value = "/{id}")
    public Long deleteSpot(@PathVariable("id") Long spotId) {
        return spotService.deleteSpot(spotId);
    }

    @GetMapping
    public List<SpotDTO> getUserOwnedSpots(@AuthenticationPrincipal AuthUser principal) {
        return spotService.getUserOwnedSpots(principal.getUserId());
    }
}
