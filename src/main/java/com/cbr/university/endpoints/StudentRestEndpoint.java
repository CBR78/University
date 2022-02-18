package com.cbr.university.endpoints;

import com.cbr.university.model.Student;
import com.cbr.university.service.BaseService;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.ok;

public class StudentRestEndpoint {
    private final BaseService<Student> studentService;
    private final StudentHandler handler = new StudentHandler();

    public StudentRestEndpoint(BaseService<Student> studentService) {
        this.studentService = studentService;
    }

    RouterFunction<ServerResponse> route = RouterFunctions.route()
            .path("/endpoint/student", builder -> builder
                    .GET(handler::getAllStudents)
                    .POST(handler::addStudent)
                    .PUT(handler::updateStudent)
                    .DELETE("/{id}", handler::deleteStudent))
            .build();

    public class StudentHandler {

        public ServerResponse getAllStudents(ServerRequest request) {
            List<Student> students = studentService.getAll();
            return ok().contentType(APPLICATION_JSON).body(students);
        }

        public ServerResponse addStudent(ServerRequest request) throws ServletException, IOException {
            Student student = request.body(Student.class);
            Student createdStudent = studentService.create(student);
            return ok().contentType(APPLICATION_JSON).body(createdStudent);
        }

        public ServerResponse updateStudent(ServerRequest request) throws ServletException, IOException {
            Student student = request.body(Student.class);
            Student updatedStudent = studentService.update(student);
            return ok().contentType(APPLICATION_JSON).body(updatedStudent);
        }

        public ServerResponse deleteStudent(ServerRequest request) {
            int id = Integer.parseInt(request.pathVariable("id"));
            studentService.deleteById(id);
            return ok().build();
        }
    }
}
