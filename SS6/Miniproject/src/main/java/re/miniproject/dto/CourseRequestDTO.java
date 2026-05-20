package com.bookmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {
    @NotBlank(message = "Course name must not be blank")
    private String name;

    private String description;

    @NotNull(message = "Course price must not be null")
    @Positive(message = "Course price must be positive")
    private Double price;
}
