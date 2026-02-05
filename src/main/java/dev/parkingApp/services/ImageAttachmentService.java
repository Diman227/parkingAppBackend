package dev.parkingApp.services;

import dev.parkingApp.dtos.request.ImageRequest;
import dev.parkingApp.dtos.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageAttachmentService {

    private final FileService fileService;

    List<ImageResponse> attachImagesToReview(Long reviewId, List<ImageRequest> images) {

        List<ImageResponse> response = new ArrayList<>();
        List<String> filesNames = fileService.addFiles(getFilesFromImageDTOs(images));

        filesNames.forEach( fileName -> response.add(new ImageResponse(null, fileName, null, reviewId)));

        return response;
    }

    List<ImageResponse> attachImagesToSpot(Long spotId, List<ImageRequest> images) {

        List<ImageResponse> response = new ArrayList<>();
        List<String> filesNames = fileService.addFiles(getFilesFromImageDTOs(images));

        filesNames.forEach( fileName -> response.add(new ImageResponse(null, fileName, spotId, null)));

        return response;
    }

    MultipartFile[] getFilesFromImageDTOs(List<ImageRequest> images) {

        return images.stream()
                .filter(Objects::nonNull)
                .map(ImageRequest::getImage)
                .filter(image -> !image.isEmpty())
                .toArray(MultipartFile[]::new);
    }
}
