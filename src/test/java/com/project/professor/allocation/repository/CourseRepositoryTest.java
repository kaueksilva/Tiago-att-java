package com.project.professor.allocation.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
@Rollback(false)
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepo;

	@Test
	public void insertCourse() {
		Course course = new Course();
		course.setName("Análise e Desenvolvimento de Sistemas");

		Course course2 = courseRepo.save(course);

		System.out.println(course2);
	}
}