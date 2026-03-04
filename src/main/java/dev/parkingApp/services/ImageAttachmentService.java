package dev.parkingApp.services;

import dev.parkingApp.dtos.kafka.ImageMessage;
import dev.parkingApp.dtos.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageAttachmentService {

    private final FileService fileService;

    public List<ImageResponse> attachRequestImagesToReview(Long reviewId, List<MultipartFile> images) {

        List<ImageResponse> response = new ArrayList<>();
        List<String> filesNames = fileService.addFiles(images);
        filesNames.forEach( fileName -> response.add(new ImageResponse(null, fileName, null, reviewId)));
        return response;

    }

    public List<ImageResponse> attachRequestImagesToSpot(Long spotId, List<MultipartFile> images) {

        List<ImageResponse> response = new ArrayList<>();
        List<String> filesNames = fileService.addFiles(images);
        filesNames.forEach( fileName -> response.add(new ImageResponse(null, fileName, spotId, null)));

        return response;
    }

    public List<ImageResponse> attachMessageImagesToSpot(Long spotId, List<ImageMessage> images) {

        return images.stream()
                .map(msg -> new ImageResponse(null, msg.getImageUrl(), spotId, null))
                .collect(Collectors.toList());
    }

}
