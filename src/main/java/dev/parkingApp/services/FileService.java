package dev.parkingApp.services;

import dev.parkingApp.exceptions.FailedFileDeleteException;
import dev.parkingApp.exceptions.FailedFileUploadException;
import io.minio.*;

import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    public ResponseEntity<String> addFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (Exception e) {
            throw new FailedFileUploadException("File wasn't uploaded with name - " + fileName);
        }
    }

//    public ResponseEntity<Resource> getFile(String filename) {
//        try {
//            InputStream stream = minioClient.getObject(
//                    GetObjectArgs.builder()
//                            .bucket(bucketName)
//                            .object(filename)
//                            .build()
//            );
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .body(new InputStreamResource(stream));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

    // todo https://github.com/minio/minio-java/blob/master/examples/GetPresignedObjectUrl.java

//    public String getImageUrl(String fileName) {
//        try {
//            return minioClient.getPresignedObjectUrl(
//                    GetPresignedObjectUrlArgs.builder()
//                            .method(Method.GET)
//                            .bucket(bucketName)
//                            .object(fileName)
//                            .expiry(60 * 60 * 24)
//                            .build()
//            );
//        }
//    }

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

}
