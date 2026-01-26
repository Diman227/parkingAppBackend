package dev.parkingApp.mappers;

import dev.parkingApp.dtos.request.ReviewRequest;
import dev.parkingApp.dtos.response.ReviewResponse;
import dev.parkingApp.entities.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    // Entity --> Response

    ReviewResponse toReviewResponse(ReviewEntity review);

    List<ReviewResponse> toListReviewResponses(List<ReviewEntity> reviews);

    // Request --> Entity

    // todo check mapping
    ReviewEntity toReviewEntity(ReviewRequest reviewRequest);

}
