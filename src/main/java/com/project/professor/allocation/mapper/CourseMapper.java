package com.project.professor.allocation.mapper;

import com.project.professor.allocation.dto.CourseRequest;
import com.project.professor.allocation.dto.CourseResponse;
import com.project.professor.allocation.entity.Course;

public class CourseMapper {

    public static Course toEntity(CourseRequest request) {
        Course course = new Course();
        course.setName(request.name());
        return course;
    }

    public static Course toEntity(CourseRequest request, Long id) {
        Course course = toEntity(request);
        course.setId(id);
        return course;
    }

    public static CourseResponse toResponse(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseResponse(course.getId(), course.getName());
    }
}