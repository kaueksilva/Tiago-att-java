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

import com.project.professor.allocation.dto.AllocationRequest;
import com.project.professor.allocation.dto.AllocationResponse;
import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.mapper.AllocationMapper;
import com.project.professor.allocation.service.AllocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Allocations")
@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        super();
        this.allocationService = allocationService;
    }

    @Operation(summary = "Find all allocations")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AllocationResponse>> findAll() {
        List<Allocation> allocations = allocationService.findAll();
        List<AllocationResponse> responses = allocations.stream()
                .map(AllocationMapper::toResponse)
                .toList();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Find an allocation")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    	@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllocationResponse> findById(@PathVariable(name = "allocation_id") Long id) {
        Allocation allocation = allocationService.findById(id);
        return new ResponseEntity<>(AllocationMapper.toResponse(allocation), HttpStatus.OK);
    }

    @Operation(summary = "Find allocations by professor")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping(path = "/professor/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AllocationResponse>> findByProfessor(@PathVariable(name = "professor_id") Long id) {
        List<Allocation> allocations = allocationService.findByProfessor(id);
        List<AllocationResponse> responses = allocations.stream()
                .map(AllocationMapper::toResponse)
                .toList();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Find allocations by course")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping(path = "/course/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AllocationResponse>> findByCourse(@PathVariable(name = "course_id") Long id) {
        List<Allocation> allocations = allocationService.findByCourse(id);
        List<AllocationResponse> responses = allocations.stream()
                .map(AllocationMapper::toResponse)
                .toList();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Save an allocation")
    @ApiResponses({
    	@ApiResponse(responseCode = "201", description = "Created"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllocationResponse> save(@Valid @RequestBody AllocationRequest request) {
        Allocation allocation = AllocationMapper.toEntity(request);
        allocation = allocationService.save(allocation);
        return new ResponseEntity<>(AllocationMapper.toResponse(allocation), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an allocation")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "OK"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
    	@ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllocationResponse> update(@PathVariable(name = "allocation_id") Long id,
                                                   @Valid @RequestBody AllocationRequest request) {
        Allocation allocation = AllocationMapper.toEntity(request, id);
        allocation = allocationService.update(allocation);
        return new ResponseEntity<>(AllocationMapper.toResponse(allocation), HttpStatus.OK);
    }

    @Operation(summary = "Delete an allocation")
    @ApiResponses({
    	@ApiResponse(responseCode = "204", description = "No Content"),
    	@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @DeleteMapping(path = "/{allocation_id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "allocation_id") Long id) {
        allocationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}