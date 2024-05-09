package com.cbr.university.controller_rest;

import com.cbr.university.model.Student;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/students")
@Validated
public class StudentRestController {
    private final BaseService<Student> studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student add(@Validated(Create.class) @RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping
    public Student update(@Validated(Update.class) @RequestBody Student student) {
        return studentService.update(student);
    }

    @DeleteMapping("{id}")
    public void delete(
            @NotNull(message = "Request must include a Student id")
            @IdExistsInDb(typeObject = "Student", message = "This Student id is not in the database")
            @PathVariable Integer id) {
        studentService.deleteById(id);
    }
}
