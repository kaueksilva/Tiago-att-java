package com.project.professor.allocation.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record AllocationResponse(
        Long id,
        DayOfWeek dayOfWeek,
        LocalTime startHour,
        LocalTime endHour,
        ProfessorResponse professor,
        CourseResponse course) {
}