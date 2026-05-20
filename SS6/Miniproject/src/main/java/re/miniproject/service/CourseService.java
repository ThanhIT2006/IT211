package com.bookmanager.service;

import com.bookmanager.dto.CoursePatchDTO;
import com.bookmanager.dto.CourseRequestDTO;
import com.bookmanager.dto.CourseResponseDTO;
import com.bookmanager.entity.Course;
import com.bookmanager.exception.ResourceNotFoundException;
import com.bookmanager.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final FileStorageService fileStorageService;

    public CourseService(CourseRepository courseRepository, FileStorageService fileStorageService) {
        this.courseRepository = courseRepository;
        this.fileStorageService = fileStorageService;
    }

    public Page<CourseResponseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(this::toResponseDTO);
    }

    public CourseResponseDTO getCourseById(Long id) {
        Course course = findCourseById(id);
        return toResponseDTO(course);
    }

    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());

        Course savedCourse = courseRepository.save(course);
        return toResponseDTO(savedCourse);
    }

    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto) {
        Course course = findCourseById(id);

        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setPrice(dto.getPrice());

        Course savedCourse = courseRepository.save(course);
        return toResponseDTO(savedCourse);
    }

    public CourseResponseDTO patchCourse(Long id, CoursePatchDTO dto) {
        Course course = findCourseById(id);

        if (dto.getName() != null) {
            if (dto.getName().isBlank()) {
                throw new IllegalArgumentException("Tên khóa học không được để trống");
            }
            course.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            course.setDescription(dto.getDescription());
        }

        if (dto.getPrice() != null) {
            course.setPrice(dto.getPrice());
        }

        Course savedCourse = courseRepository.save(course);
        return toResponseDTO(savedCourse);
    }

    public void deleteCourse(Long id) {
        Course course = findCourseById(id);

        if (course.getImageUrl() != null) {
            fileStorageService.deleteFile(course.getImageUrl());
        }

        courseRepository.delete(course);
    }

    public CourseResponseDTO uploadImage(Long id, MultipartFile file) {
        Course course = findCourseById(id);

        if (course.getImageUrl() != null) {
            fileStorageService.deleteFile(course.getImageUrl());
        }

        String imageUrl = fileStorageService.saveFile(file);
        course.setImageUrl(imageUrl);

        Course savedCourse = courseRepository.save(course);
        return toResponseDTO(savedCourse);
    }

    public void deleteImage(Long id) {
        Course course = findCourseById(id);

        if (course.getImageUrl() == null || course.getImageUrl().isBlank()) {
            throw new ResourceNotFoundException("Khóa học chưa có ảnh");
        }

        fileStorageService.deleteFile(course.getImageUrl());
        course.setImageUrl(null);
        courseRepository.save(course);
    }

    private Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khóa học với id: " + id));
    }

    private CourseResponseDTO toResponseDTO(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getPrice(),
                course.getImageUrl()
        );
    }
}
