package com.cbr.university.service;

import com.cbr.university.model.Teacher;
import com.cbr.university.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements BaseService<Teacher> {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher create(Teacher teacher) {
        return teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public void deleteById(int id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(int id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public boolean existsById(int id) {
        return teacherRepository.existsById(id);
    }
}
