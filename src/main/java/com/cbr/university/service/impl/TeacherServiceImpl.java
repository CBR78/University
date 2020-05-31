package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;

@Service
public class TeacherServiceImpl implements BaseService<Teacher> {

    private BaseDao<Teacher> teacherDao;

    @Autowired
    public TeacherServiceImpl(BaseDao<Teacher> teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void create(Teacher teacher) {
        teacherDao.create(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherDao.update(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherDao.delete(teacher);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherDao.getAll();
    }

    @Override
    public Teacher getById(int id) {
        return teacherDao.getById(id);
    }
}
