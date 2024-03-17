package com.feastfreedom.service;

import com.feastfreedom.dto.request.KitchenRequest;
import com.feastfreedom.entity.Kitchen;
import com.feastfreedom.entity.UploadFile;
import com.feastfreedom.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    @Value("${file.dir}")
    private String fileDir;


    public void storeFile(String dataURI, Kitchen kitchen) throws IOException {
        if (dataURI.isEmpty()) {
            return;
        }

        String[] parts = dataURI.split(",");
        String mimeType = parts[0].split(";")[0].split(":")[1];
        String base64Data = parts[1];


        // Decode Base64 String to byte array
        byte[] decodedImage= Base64.getDecoder().decode(base64Data);

        // Created stored filename made by UUID
        String storeFileName = createStoreFileName();

        // Save image file
        String fileExtension = mimeType.split("/")[1];
        String filePath = getFullPath(storeFileName + "." + fileExtension);
        Files.write(Paths.get(filePath), decodedImage);

        // Save upload file into db
        UploadFile file = UploadFile.builder()
                .storeFileName(storeFileName + "." + fileExtension)
                .kitchen(kitchen)
                .build();
        fileRepository.save(file);
    }

    public String getFileAsBase64(String fileName) throws IOException {
        byte[] fileBytes = getFileBytes(fileName);
        String mimeType = determineMimeType(fileName);
        String base64Data = encodeByBytesToBase64(fileBytes);
        return "data:" + mimeType + ";base64," + base64Data;
    }

    public byte[] getFileBytes(String fileName) throws IOException {
        String filePath = getFullPath(fileName);
        File file = new File(filePath);
        return Files.readAllBytes(file.toPath());
    }

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public UploadFile findByKitchenId(Long kitchenId) {
        return this.fileRepository.findByKitchenId(kitchenId).orElseThrow();
    }

    private String encodeByBytesToBase64(byte[] bytes) {
        System.out.println(Base64.getEncoder().encodeToString(bytes));
        return Base64.getEncoder().encodeToString(bytes);
    }

    private String createStoreFileName() {
        return UUID.randomUUID().toString();
    }

    private String determineMimeType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return switch (extension) {
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "gif" -> "image/gif";
            default -> "application/octet-stream";
        };
    }

}
