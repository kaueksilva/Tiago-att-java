package com.project.professor.allocation.mapper;

import com.project.professor.allocation.dto.DepartmentRequest;
import com.project.professor.allocation.dto.DepartmentResponse;
import com.project.professor.allocation.entity.Department;

public class DepartmentMapper {

    public static Department toEntity(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.name());
        return department;
    }

    public static Department toEntity(DepartmentRequest request, Long id) {
        Department department = toEntity(request);
        department.setId(id);
        return department;
    }

    public static DepartmentResponse toResponse(Department department) {
        if (department == null) {
            return null;
        }
        return new DepartmentResponse(department.getId(), department.getName());
    }
}