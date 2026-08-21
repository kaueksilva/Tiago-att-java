package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.dto.CourseRequest;
import com.project.professor.allocation.dto.CourseResponse;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.mapper.CourseMapper;
import com.project.professor.allocation.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Courses")
@RestController
@RequestMapping(path = "/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        super();
        this.courseService = courseService;
    }

    @Operation(summary = "Find all courses")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CourseResponse>> findAll() {
        List<Course> courses = courseService.findAll();
        List<CourseResponse> responses = courses.stream()
                .map(CourseMapper::toResponse)
                .toList();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Find a course")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    	@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(path = "/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseResponse> findById(@PathVariable(name = "course_id") Long id) {
        Course course = courseService.findById(id);
        return new ResponseEntity<>(CourseMapper.toResponse(course), HttpStatus.OK);
    }

    @Operation(summary = "Save a course")
    @ApiResponses({
    	@ApiResponse(responseCode = "201", description = "Created"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseResponse> save(@Valid @RequestBody CourseRequest request) {
        Course course = CourseMapper.toEntity(request);
        course = courseService.save(course);
        return new ResponseEntity<>(CourseMapper.toResponse(course), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a course")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    	@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(path = "/{course_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseResponse> update(@PathVariable(name = "course_id") Long id,
                                               @Valid @RequestBody CourseRequest request) {
        Course course = CourseMapper.toEntity(request, id);
        course = courseService.update(course);
        return new ResponseEntity<>(CourseMapper.toResponse(course), HttpStatus.OK);
    }

    @Operation(summary = "Delete a course")
    @ApiResponses({
    	@ApiResponse(responseCode = "204", description = "No Content"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @DeleteMapping(path = "/{course_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "course_id") Long id) {
        courseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}