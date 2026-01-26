package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.SpotRequest;
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
    public SpotRequest addSpot(@RequestBody SpotRequest spotDTO) {
        return spotService.addSpot(spotDTO);
    }

    @PutMapping
    @PreAuthorize("#spotDTO.ownerId == authentication.principal.userId")
    public SpotRequest updateSpot(@RequestBody SpotRequest spotDTO) {
        return spotService.updateSpot(spotDTO);
    }

    // todo security?
    @DeleteMapping(value = "/{id}")
    public Long deleteSpot(@PathVariable("id") Long spotId) {
        return spotService.deleteSpot(spotId);
    }

    @GetMapping("/user")
    public List<SpotRequest> getUserOwnedSpots(@AuthenticationPrincipal AuthUser principal) {
        return spotService.getUserOwnedSpots(principal.getUserId());
    }

    @GetMapping
    public List<SpotRequest> getAllSpots() {
        return spotService.getAllSpots();
    }
}
