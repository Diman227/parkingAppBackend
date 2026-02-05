package dev.parkingApp.services;

import dev.parkingApp.exceptions.FailedFileDeleteException;
import dev.parkingApp.exceptions.FailedFileUploadException;
import io.minio.*;

import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

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

        } catch (Exception e) {
            throw new FailedFileUploadException("File wasn't uploaded with name - " + fileName);
        }
    }

    public List<String> addFiles(MultipartFile[] files) {
        List<String> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            responses.add(addFile(file));

        }
        return responses;
    }

    // todo https://github.com/minio/minio-java/blob/master/examples/GetPresignedObjectUrl.java

    public String getImageUrl(String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(fileName)
                            .expiry(60 * 60 * 24)
                            .build()
            );

            // not working without, IDEA's solution/hint
        } catch (ServerException | InternalException | XmlParserException | InvalidResponseException |
                 InvalidKeyException | NoSuchAlgorithmException | IOException | ErrorResponseException |
                 InsufficientDataException e) {
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
