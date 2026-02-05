package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.ImageRequest;
import dev.parkingApp.dtos.response.ImageResponse;
import dev.parkingApp.entities.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageEntity toImageEntity(ImageResponse imageResponse);

    List<ImageEntity> toListImageEntities(List<ImageResponse> imageResponse);

    // Request --> Entity

    @Mapping(target = "spotId", source = "spot.id")
    @Mapping(target = "reviewId", source = "review.id")
    ImageResponse toImageResponse(ImageEntity image);

    List<ImageResponse> toListImageResponses(List<ImageEntity> imageEntities);
}
