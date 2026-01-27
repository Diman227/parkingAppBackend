package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.dtos.response.SpotResponse;
import dev.parkingApp.services.SpotService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "*api/base/spots", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class SpotController {

    private final SpotService spotService;

    @PostMapping
    @PreAuthorize("#spotDTO.ownerId == authentication.principal.userId")
    public SpotResponse addSpot(@RequestBody @Validated(SpotRequest.Create.class) SpotRequest spotDTO) {
        return spotService.addSpot(spotDTO);
    }

    @PutMapping
    @PreAuthorize("#spotDTO.ownerId == authentication.principal.userId")
    public SpotResponse updateSpot(@RequestBody @Validated(SpotRequest.Update.class) SpotRequest spotDTO) {
        return spotService.updateSpot(spotDTO);
    }

    @DeleteMapping("/{id}")
    public Long deleteSpot(@PathVariable("id") @NotNull @Positive Long spotId) {
        return spotService.deleteSpot(spotId);
    }

    @GetMapping("/user")
    public List<SpotResponse> getUserOwnedSpots(@AuthenticationPrincipal AuthUser principal) {
        return spotService.getUserOwnedSpots(principal.getUserId());
    }

    @GetMapping
    public List<SpotResponse> getAllSpots() {
        return spotService.getAllSpots();
    }
}
