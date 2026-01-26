package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.response.SpotResponse;
import dev.parkingApp.entities.SpotEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CoordinatesMapper.class, ReviewMapper.class, UserMapper.class})
public interface SpotMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    SpotRequest toSpotDTO(SpotEntity spot);

    SpotEntity toSpotEntity(SpotRequest spotDTO);

    List<SpotRequest> toListSpotDTOs(List<SpotEntity> spots);

    List<SpotEntity> toListSpotEntities(List<SpotRequest> spots);

    SpotResponse toSpotWithOwnerDTO(SpotEntity spot);
}
