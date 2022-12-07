package com.cbr.university.controller_rest;

import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
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

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/teachers")
@Validated
public class TeacherRestController {
    private final BaseService<Teacher> teacherService;

    @GetMapping
    public List<Teacher> getAll() {
        return teacherService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher add(@Validated(Create.class) @RequestBody Teacher teacher) {
        return teacherService.create(teacher);
    }

    @PutMapping
    public Teacher update(@Validated(Update.class) @RequestBody Teacher teacher) {
        return teacherService.update(teacher);
    }

    @DeleteMapping("{id}")
    public void delete(
            @NotNull(message = "Request must include a Teacher id")
            @IdExistsInDb(typeObject = "Teacher", message = "This Teacher id is not in the database")
            @PathVariable Integer id) {
        teacherService.deleteById(id);
    }
}
