package dev.parkingApp.services;

import dev.parkingApp.dtos.request.ImageRequest;
import dev.parkingApp.dtos.response.ImageResponse;
import dev.parkingApp.repositories.ImageRepository;
import dev.parkingApp.repositories.SpotRepository;
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

    private final ImageRepository imageRepository;
    private final SpotRepository spotRepository;

    List<ImageResponse> attachImagesToReview(Long reviewId, List<ImageRequest> images) {

        List<ImageResponse> response = new ArrayList<>();
        List<String> filesNames = fileService.addFiles(getFilesFromImageDTOs(images));

        for(String fileName : filesNames) {
            response.add(new ImageResponse(null, fileName, null, reviewId));
        }

        return response;
    }

    List<ImageResponse> attachImagesToSpot(Long spotId, List<ImageRequest> images) {

        //todo
        return null;
    }

    MultipartFile[] getFilesFromImageDTOs(List<ImageRequest> images) {

        return images.stream()
                .filter(Objects::nonNull)
                .map(ImageRequest::getImage)
                .filter(image -> !image.isEmpty())
                .toArray(MultipartFile[]::new);
    }
}
