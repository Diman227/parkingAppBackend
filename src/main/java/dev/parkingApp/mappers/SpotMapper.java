package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.dtos.response.SpotResponse;
import dev.parkingApp.entities.SpotEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CoordinatesMapper.class, ReviewMapper.class, UserMapper.class})
public interface SpotMapper {

    // Entity ---> Response

    SpotResponse toSpotResponse(SpotEntity spot);

    List<SpotResponse> toListSpotResponses(List<SpotEntity> spots);

    // Request ---> Entity

    SpotEntity toSpotEntity(SpotRequest spotRequest);

}
