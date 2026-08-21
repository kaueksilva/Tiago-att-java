package com.project.professor.allocation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProfessorRequest(
        @NotBlank(message = "O nome do professor é obrigatório.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String name,

        @NotBlank(message = "O CPF do professor é obrigatório.")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
        String cpf,

        @NotNull(message = "O ID do departamento é obrigatório.")
        Long departmentId) {
}