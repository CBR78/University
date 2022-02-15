package com.cbr.university.controller_rest;

import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("rest/teachers")
@Validated
public class TeacherRestController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private final BaseService<Teacher> teacherService;

    public TeacherRestController(BaseService<Teacher> teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        List<Teacher> teachers = teacherService.getAll();
        return ResponseEntity
                .ok()
                .header(CUSTOM_HEADER_NAME, "All objects Teacher found. Number of objects " + teachers.size())
                .body(teachers);
    }

    @PostMapping
    public ResponseEntity<Teacher> add(@Validated(Create.class) @RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.create(teacher);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(CUSTOM_HEADER_NAME, "Created Teacher object with id " + createdTeacher.getId())
                .body(createdTeacher);
    }

    @PutMapping
    public ResponseEntity<Teacher> update(@Validated(Update.class) @RequestBody Teacher teacher) {
        Teacher updatedTeacher = teacherService.update(teacher);
        return ResponseEntity
                .ok()
                .header(CUSTOM_HEADER_NAME, "Updated Teacher object with id " + updatedTeacher.getId())
                .body(updatedTeacher);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Teacher> delete(
            @NotNull(message = "Request must include a Teacher id")
            @IdExistsInDb(typeObject = "Teacher", message = "This Teacher id is not in the database")
            @PathVariable Integer id) {
        teacherService.deleteById(id);
        return ResponseEntity
                .ok()
                .header(CUSTOM_HEADER_NAME, "Deleted Teacher object with id " + id)
                .build();
    }
}
