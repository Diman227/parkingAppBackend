package dev.parkingApp.controllers;

import dev.parkingApp.dtos.SpotDTO;
import dev.parkingApp.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(value = "*api/base/", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping(value = "spots")
    public String hello(){
        return "hello";
    }



    @PostMapping(value = "spots")
    public SpotDTO addSpot(@RequestBody SpotDTO spotDTO) {
        return this.spotService.addSpot(spotDTO);
    }

    @PutMapping(value = "spots")
    public SpotDTO updateSpot(@RequestBody SpotDTO spotDTO) {
        return this.spotService.updateSpot(spotDTO);
    }

    @DeleteMapping(value = "spots/{id}")
    public Long deleteSpot(@PathVariable("id") Long spotId) {
        return spotService.deleteSpot(spotId);
    }
}
