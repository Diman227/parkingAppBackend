package dev.parkingApp.mappers;

import dev.parkingApp.dtos.ReviewDTO;
import dev.parkingApp.entities.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "spotId", source = "spot.id")
    @Mapping(target = "authorId", source = "author.id")
    ReviewDTO toReviewDTO(ReviewEntity review);

    ReviewEntity toReviewEntity(ReviewDTO reviewDTO);

    // todo добавить методы для списков и также просмотреть где еще такие методы
}
