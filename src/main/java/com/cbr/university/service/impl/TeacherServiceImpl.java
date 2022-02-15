package com.cbr.university.service.impl;

import com.cbr.university.model.Teacher;
import com.cbr.university.repository.TeacherRepository;
import com.cbr.university.service.BaseService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class TeacherServiceImpl implements BaseService<Teacher> {

    private final TeacherRepository teacherRepository;
    private final EntityManager entityManager;

    public TeacherServiceImpl(TeacherRepository teacherRepository, EntityManager entityManager) {
        this.teacherRepository = teacherRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteById(int id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAll() {
        entityManager.clear();
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
