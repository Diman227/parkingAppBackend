package dev.parkingApp.mappers;

import dev.parkingApp.dtos.CoordinatesDTO;
import dev.parkingApp.dtos.SpotDTO;
import dev.parkingApp.entities.SpotEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CoordinatesMapper.class, ReviewMapper.class})
public interface SpotMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    SpotDTO toSpotDTO(SpotEntity spot);

    SpotEntity toSpotEntity(SpotDTO spotDTO);
}
