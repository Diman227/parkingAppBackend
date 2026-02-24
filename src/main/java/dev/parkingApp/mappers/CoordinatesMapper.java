package dev.parkingApp.mappers;

import dev.parkingApp.dtos.kafka.CoordinatesMessage;
import dev.parkingApp.dtos.request.CoordinatesRequest;
import dev.parkingApp.dtos.response.CoordinatesResponse;
import dev.parkingApp.entities.CoordinatesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoordinatesMapper {

    CoordinatesResponse toCoordinatesDTO(CoordinatesEntity coordinates);

    CoordinatesEntity toCoordinatesEntityFromRequest(CoordinatesRequest coordinatesDTO);

    CoordinatesEntity toCoordinatesEntityFromMessage(CoordinatesMessage coordinatesMessage);
}
