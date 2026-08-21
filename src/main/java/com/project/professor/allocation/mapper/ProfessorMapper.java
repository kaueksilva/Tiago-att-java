package com.project.professor.allocation.mapper;

import com.project.professor.allocation.dto.ProfessorRequest;
import com.project.professor.allocation.dto.ProfessorResponse;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

public class ProfessorMapper {

    public static Professor toEntity(ProfessorRequest request) {
        Professor professor = new Professor();
        professor.setName(request.name());
        professor.setCpf(request.cpf());

        Department department = new Department();
        department.setId(request.departmentId());
        professor.setDepartment(department);

        return professor;
    }

    public static Professor toEntity(ProfessorRequest request, Long id) {
        Professor professor = toEntity(request);
        professor.setId(id);
        return professor;
    }

    public static ProfessorResponse toResponse(Professor professor) {
        if (professor == null) {
            return null;
        }
        return new ProfessorResponse(
                professor.getId(),
                professor.getName(),
                professor.getCpf(),
                DepartmentMapper.toResponse(professor.getDepartment()));
    }
}