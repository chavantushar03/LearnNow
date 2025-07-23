package com.learnnow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponseDTO extends BaseDTO {
//    private Long id;
    private String title;
    private String description;
    private String teacherName;
    private boolean active;
}
