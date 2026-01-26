package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.ReviewRequest;
import dev.parkingApp.entities.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "spotId", source = "spot.id")
    @Mapping(target = "authorId", source = "author.id")
    ReviewRequest toReviewDTO(ReviewEntity review);

    ReviewEntity toReviewEntity(ReviewRequest reviewDTO);

    List<ReviewRequest> toListReviewDTOs(List<ReviewEntity> reviews);

    List<ReviewEntity> toListReviewEntities(List<ReviewRequest> reviews);

}
