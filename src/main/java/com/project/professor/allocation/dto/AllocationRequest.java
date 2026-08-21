package com.project.professor.allocation.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record AllocationRequest(
        @NotNull(message = "O dia da semana é obrigatório.")
        DayOfWeek dayOfWeek,

        @NotNull(message = "O horário inicial é obrigatório.")
        LocalTime startHour,

        @NotNull(message = "O horário final é obrigatório.")
        LocalTime endHour,

        @NotNull(message = "O ID do professor é obrigatório.")
        Long professorId,

        @NotNull(message = "O ID do curso é obrigatório.")
        Long courseId) {
}