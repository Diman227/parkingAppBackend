package dev.parkingApp.mappers;

import dev.parkingApp.dtos.kafka.SpotMessage;
import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.response.SpotResponse;
import dev.parkingApp.entities.SpotEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = {CoordinatesMapper.class, ReviewMapper.class, UserMapper.class},
        imports = {LocalDateTime.class})
public interface SpotMapper {

    // Entity ---> Response

    SpotResponse toSpotResponse(SpotEntity spot);

    List<SpotResponse> toListSpotResponses(List<SpotEntity> spots);

    // Request ---> Entity

    @Mapping(target = "rate", ignore = true)
    SpotEntity toSpotEntity(SpotRequest spotRequest);

    @Mapping(target = "coordinates", source = "coordinates")
    @Mapping(target = "ownerId", source = "ownerId")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "externalOwnerId", ignore = true)
    SpotEntity createSpotEntityFromSpotRequest(SpotRequest spotRequest);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    SpotEntity updateSpotEntity(@MappingTarget SpotEntity spot, SpotRequest spotRequest);

    // Message ---> Entity

    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "externalOwnerId", source = "ownerId")
    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "ownerId", expression = "java(null)")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    SpotEntity createSpotEntityFromSpotMessage(SpotMessage spotMessage);

}
