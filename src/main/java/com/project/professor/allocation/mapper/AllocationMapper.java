package com.project.professor.allocation.mapper;

import com.project.professor.allocation.dto.AllocationRequest;
import com.project.professor.allocation.dto.AllocationResponse;
import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

public class AllocationMapper {

    public static Allocation toEntity(AllocationRequest request) {
        Allocation allocation = new Allocation();
        allocation.setDayOfWeek(request.dayOfWeek());
        allocation.setStartHour(request.startHour());
        allocation.setEndHour(request.endHour());

        Professor professor = new Professor();
        professor.setId(request.professorId());
        allocation.setProfessor(professor);

        Course course = new Course();
        course.setId(request.courseId());
        allocation.setCourse(course);

        return allocation;
    }

    public static Allocation toEntity(AllocationRequest request, Long id) {
        Allocation allocation = toEntity(request);
        allocation.setId(id);
        return allocation;
    }

    public static AllocationResponse toResponse(Allocation allocation) {
        if (allocation == null) {
            return null;
        }
        return new AllocationResponse(
                allocation.getId(),
                allocation.getDayOfWeek(),
                allocation.getStartHour(),
                allocation.getEndHour(),
                ProfessorMapper.toResponse(allocation.getProfessor()),
                CourseMapper.toResponse(allocation.getCourse()));
    }
}