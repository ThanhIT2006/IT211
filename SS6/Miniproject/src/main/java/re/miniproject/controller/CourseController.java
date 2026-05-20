package com.bookmanager.controller;

import com.bookmanager.dto.CoursePatchDTO;
import com.bookmanager.dto.CourseRequestDTO;
import com.bookmanager.dto.CourseResponseDTO;
import com.bookmanager.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO dto) {
        CourseResponseDTO createdCourse = courseService.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDTO dto
    ) {
        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> patchCourse(
            @PathVariable Long id,
            @Valid @RequestBody CoursePatchDTO dto
    ) {
        return ResponseEntity.ok(courseService.patchCourse(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<CourseResponseDTO> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.ok(courseService.uploadImage(id, file));
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        courseService.deleteImage(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(Map.of("message", "CourseCatalog API is running"));
    }
}