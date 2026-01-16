package dev.parkingApp.mappers;

import dev.parkingApp.dtos.ReviewDTO;
import dev.parkingApp.entities.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "spotId", source = "spot.id")
    @Mapping(target = "authorId", source = "author.id")
    ReviewDTO toReviewDTO(ReviewEntity review);

    ReviewEntity toReviewEntity(ReviewDTO reviewDTO);

    List<ReviewDTO> toListReviewDTOs(List<ReviewEntity> reviews);

    List<ReviewEntity> toListReviewEntities(List<ReviewDTO> reviews);

}
