package com.bookmanager.service;

import com.bookmanager.exception.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path uploadPath;

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png");

    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
        this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.uploadPath);
        } catch (IOException e) {
            throw new FileStorageException("Không thể tạo thư mục upload");
        }
    }

    public String saveFile(MultipartFile file) {
        validateFile(file);

        String originalFilename = file.getOriginalFilename();
        String extension = getExtension(originalFilename);
        String newFilename = UUID.randomUUID() + "." + extension;

        Path targetPath = uploadPath.resolve(newFilename);

        try {
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + newFilename;
        } catch (IOException e) {
            throw new FileStorageException("Không thể lưu file ảnh");
        }
    }

    public void deleteFile(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            return;
        }

        String filename = imageUrl.replace("/uploads/", "");
        Path filePath = uploadPath.resolve(filename).normalize();

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new FileStorageException("Không thể xóa file ảnh");
        }
    }

    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File không được để trống");
        }

        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null || originalFilename.isBlank()) {
            throw new IllegalArgumentException("Tên file không hợp lệ");
        }

        String extension = getExtension(originalFilename);

        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException("Chỉ chấp nhận file jpg, jpeg hoặc png");
        }

        String contentType = file.getContentType();

        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("File upload phải là ảnh");
        }
    }

    private String getExtension(String filename) {
        int dotIndex = filename.lastIndexOf(".");

        if (dotIndex == -1) {
            throw new IllegalArgumentException("File không có phần mở rộng");
        }

        return filename.substring(dotIndex + 1).toLowerCase();
    }
}
