package com.project.professor.allocation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CourseRequest(
        @NotBlank(message = "O nome do curso é obrigatório.")
        @Size(max = 100, message = "O nome do curso deve ter no máximo 100 caracteres.")
        String name) {
}