package dev.parkingApp.mappers;

import dev.parkingApp.dtos.CoordinatesDTO;
import dev.parkingApp.dtos.SpotDTO;
import dev.parkingApp.dtos.SpotWithOwnerDTO;
import dev.parkingApp.entities.SpotEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CoordinatesMapper.class, ReviewMapper.class, UserMapper.class})
public interface SpotMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    SpotDTO toSpotDTO(SpotEntity spot);

    SpotEntity toSpotEntity(SpotDTO spotDTO);

    List<SpotDTO> toListSpotDTOs(List<SpotEntity> spots);

    List<SpotEntity> toListSpotEntities(List<SpotDTO> spots);

    SpotWithOwnerDTO toSpotWithOwnerDTO(SpotEntity spot);
}
