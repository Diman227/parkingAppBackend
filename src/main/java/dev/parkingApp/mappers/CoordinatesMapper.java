package dev.parkingApp.mappers;

import dev.parkingApp.dtos.response.CoordinatesResponse;
import dev.parkingApp.entities.CoordinatesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordinatesMapper {

    CoordinatesResponse toCoordinatesDTO(CoordinatesEntity coordinates);

    CoordinatesEntity toCoordinatesEntity(CoordinatesResponse coordinatesDTO);
}
