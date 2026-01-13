package dev.parkingApp.mappers;

import dev.parkingApp.dtos.CoordinatesDTO;
import dev.parkingApp.entities.CoordinatesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoordinatesMapper {

    CoordinatesDTO toCoordinatesDTO(CoordinatesEntity coordinates);

    CoordinatesEntity toCoordinatesEntity(CoordinatesDTO coordinatesDTO);
}
