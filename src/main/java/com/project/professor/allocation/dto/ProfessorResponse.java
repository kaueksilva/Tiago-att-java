package com.project.professor.allocation.dto;

public record ProfessorResponse(
        Long id,
        String name,
        String cpf,
        DepartmentResponse department) {
}