package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.TeacherRepositroy;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;

@Service
public class TeacherServiceImpl implements BaseService<Teacher> {

    private TeacherRepositroy teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherRepositroy teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void create(Teacher teacher) {
        teacherDao.saveAndFlush(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherDao.saveAndFlush(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherDao.delete(teacher);
    }

    @Override
    public List<Teacher> getAll() {
        return (List<Teacher>) teacherDao.findAll();
    }

    @Override
    public Teacher getById(int id) {
        return teacherDao.findById(id).get();
    }
}
