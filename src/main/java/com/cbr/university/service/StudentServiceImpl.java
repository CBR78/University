package com.cbr.university.service;

import com.cbr.university.model.Student;
import com.cbr.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements BaseService<Student> {
    private final StudentRepository studentRepository;
    private final EntityManager entityManager;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAll() {
        entityManager.clear();
        return studentRepository.findAll();
    }

    @Override
    public Student getById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public boolean existsById(int id) {
        return studentRepository.existsById(id);
    }
}
