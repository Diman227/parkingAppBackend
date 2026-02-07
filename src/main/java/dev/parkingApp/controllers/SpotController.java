package dev.parkingApp.controllers;

import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.auth.AuthUser;
import dev.parkingApp.dtos.response.SpotResponse;
import dev.parkingApp.services.SpotService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SpotResponse> createSpot(
            @RequestBody @Validated(SpotRequest.Create.class) SpotRequest spotDTO) {
        return new ResponseEntity<>(spotService.addSpot(spotDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{spotId}")
    @PreAuthorize("#spotDTO.ownerId == authentication.principal.userId")
    public ResponseEntity<SpotResponse> updateSpot(
            @PathVariable("spotId") Long spotId,
            @RequestBody @Validated(SpotRequest.Update.class) SpotRequest spotDTO) {
        return new ResponseEntity<>(spotService.updateSpot(spotId, spotDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{spotId}")
    public ResponseEntity<Long> deleteSpot(
            @PathVariable("spotId") @NotNull @Positive Long spotId) {
        return new ResponseEntity<>(spotService.deleteSpot(spotId), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user")
    public ResponseEntity<List<SpotResponse>> getUserOwnedSpotsWithImages(
            @AuthenticationPrincipal AuthUser principal) {
        return new ResponseEntity<>(spotService.getUserOwnedSpotsWithImages(principal.getUserId()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SpotResponse>> getAllSpots() {
        return new ResponseEntity<>(spotService.getAllSpots(), HttpStatus.OK);
    }

}
