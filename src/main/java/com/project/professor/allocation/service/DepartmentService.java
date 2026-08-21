package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.exception.NotFoundException;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public Department findById(Long id) {
		return departmentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Departamento não encontrado com o ID: " + id));
	}

	public Department save(Department department) {
		department.setId(null);
		return departmentRepository.save(department);
	}

	public Department update(Department department) {
		Long id = department.getId();

		if (id == null || !departmentRepository.existsById(id)) {
			throw new NotFoundException("Departamento não encontrado com o ID: " + id);
		}

		return departmentRepository.save(department);
	}

	public void deleteById(Long id) {
		if (!departmentRepository.existsById(id)) {
			throw new NotFoundException("Departamento não encontrado com o ID: " + id);
		}
		departmentRepository.deleteById(id);
	}
}