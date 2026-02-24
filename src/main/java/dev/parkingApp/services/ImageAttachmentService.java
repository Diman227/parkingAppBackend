package dev.parkingApp.services;

import dev.parkingApp.dtos.kafka.ImageMessage;
import dev.parkingApp.dtos.request.ImageRequest;
import dev.parkingApp.dtos.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageAttachmentService {

    private final FileService fileService;

    public List<ImageResponse> attachRequestImagesToReview(Long reviewId, List<ImageRequest> images) {

        List<ImageResponse> response = new ArrayList<>();
        List<String> filesNames = fileService.addFiles(getFilesFromImageRequest(images));
        filesNames.forEach( fileName -> response.add(new ImageResponse(null, fileName, null, reviewId)));

        return response;
    }

    public List<ImageResponse> attachMessageImagesToReview(Long reviewId, List<ImageMessage> images) {

        return images.stream()
                .map(msg -> new ImageResponse(null, msg.getImageUrl(), null, reviewId))
                .collect(Collectors.toList());
    }

    public List<ImageResponse> attachRequestImagesToSpot(Long spotId, List<ImageRequest> images) {

        List<ImageResponse> response = new ArrayList<>();
        List<String> filesNames = fileService.addFiles(getFilesFromImageRequest(images));
        filesNames.forEach( fileName -> response.add(new ImageResponse(null, fileName, spotId, null)));

        return response;
    }

    public List<ImageResponse> attachMessageImagesToSpot(Long spotId, List<ImageMessage> images) {

        return images.stream()
                .map(msg -> new ImageResponse(null, msg.getImageUrl(), spotId, null))
                .collect(Collectors.toList());
    }

    public MultipartFile[] getFilesFromImageRequest(List<ImageRequest> images) {

        return images.stream()
                .filter(Objects::nonNull)
                .map(ImageRequest::getImage)
                .filter(image -> !image.isEmpty())
                .toArray(MultipartFile[]::new);
    }
}
