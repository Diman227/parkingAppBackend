package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.ReviewRequest;
import dev.parkingApp.dtos.response.ReviewResponse;
import dev.parkingApp.entities.ReviewEntity;
import dev.parkingApp.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    // todo пересмотреть responseDTO и что возвращают контроллеры, как будто слишком огромные объекты
    // Entity --> Response

    @Mapping(target = "reviewedSpotId", source = "spot.id")
    @Mapping(target = "authorFullName", expression = "java(getUserFullName(review.getAuthor()))")
    ReviewResponse toReviewResponse(ReviewEntity review);

    List<ReviewResponse> toListReviewResponses(List<ReviewEntity> reviews);

    // Request --> Entity

    ReviewEntity toReviewEntity(ReviewRequest reviewRequest);

    default String getUserFullName(UserEntity author) {
        return author.getSurname() + " " + author.getName();
    }

}
