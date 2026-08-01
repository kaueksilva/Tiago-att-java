package com.project.professor.allocation.repository;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
@Rollback(false)
public class AllocationRepositoryTest {

	@Autowired
	AllocationRepository allocationRepo;

	@Test
	public void insertAllocation() {
		Allocation allocation = new Allocation();
		allocation.setDayOfWeek(DayOfWeek.MONDAY);
		allocation.setStartHour(LocalTime.of(19, 0));
		allocation.setEndHour(LocalTime.of(22, 40));

		Allocation allocation2 = allocationRepo.save(allocation);

		System.out.println(allocation2);
	}
}