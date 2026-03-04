package dev.parkingApp.services;

import dev.parkingApp.exceptions.FailedFileDeleteException;
import dev.parkingApp.exceptions.FailedFileUploadException;
import io.minio.*;

import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    private final int EXPIRY_TIME = 60 * 60 * 24;

    public String addFile(MultipartFile file) {

        if(file.isEmpty()) throw new FailedFileUploadException("Uploaded file is empty");

        String fileName = generateUniqueFileName(file.getOriginalFilename());

        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return fileName;

        } catch (Exception ex) {
            log.info("File with name - {} wasn't uploaded: {}", fileName, ex.getMessage());
            return null;
        }
    }

    public List<String> addFiles(List<MultipartFile> files) {

        return files.stream()
                .map(this::addFile)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // todo https://github.com/minio/minio-java/blob/master/examples/GetPresignedObjectUrl.java

    public String getImageUrl(String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(fileName)
                            .expiry(EXPIRY_TIME)
                            .build()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteFile(String filename) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .build()
            );
            return filename + " was deleted";
        } catch (Exception e) {
            throw new FailedFileDeleteException("Error in deleting file with name - " + filename);
        }
    }

    public String generateUniqueFileName(String fileName) {
        return UUID.randomUUID().toString().substring(0,16) + fileName.substring(fileName.lastIndexOf("."));
    }

}
