package com.cbr.university.controller_rest;

import com.cbr.university.dto.TeacherDto;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("rest/teachers")
@Validated
public class TeacherRestController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private final HttpHeaders headers = new HttpHeaders();
    private final BaseService<Teacher> teacherService;

    @Autowired
    public TeacherRestController(BaseService<Teacher> teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        List<Teacher> teachers = teacherService.getAll();
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME,
                "All objects Teacher found. Number of objects " + teachers.size());
        return new ResponseEntity<>(teachers, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Teacher> add(
            @Validated(Create.class) @RequestBody TeacherDto teacherDto) {
        Teacher teacher = new Teacher(teacherDto);
        Teacher createdTeacher = teacherService.create(teacher);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Created Teacher object with id " + createdTeacher.getId());
        return new ResponseEntity<>(createdTeacher, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Teacher> update(
            @Validated(Update.class) @RequestBody TeacherDto teacherDto) {
        Teacher teacher = new Teacher(teacherDto);
        Teacher updatedTeacher = teacherService.update(teacher);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Updated Teacher object with id " + updatedTeacher.getId());
        return new ResponseEntity<>(updatedTeacher, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Teacher> delete(
            @NotNull(message = "Request must include a Teacher id") @IdExistsInDb(typeObject = "Teacher", message = "This Teacher id is not in the database") @PathVariable Integer id) {
        teacherService.delete(teacherService.getById(id));
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Deleted Teacher object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
