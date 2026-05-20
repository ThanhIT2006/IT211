package com.bookmanager.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoursePatchDTO {
    private String name;
    private String description;
    @Positive(message = "Course price must be positive")
    private Double price;
}
