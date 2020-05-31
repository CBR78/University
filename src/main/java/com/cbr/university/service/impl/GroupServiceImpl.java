package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.Group;
import com.cbr.university.service.BaseService;

@Service
public class GroupServiceImpl implements BaseService<Group> {

    private BaseDao<Group> groupDao;

    @Autowired
    public GroupServiceImpl(BaseDao<Group> groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void create(Group group) {
        groupDao.create(group);
    }

    @Override
    public void update(Group group) {
        groupDao.update(group);
    }

    @Override
    public void delete(Group group) {
        groupDao.delete(group);
    }

    @Override
    public List<Group> getAll() {
        return groupDao.getAll();
    }

    @Override
    public Group getById(int id) {
        return groupDao.getById(id);
    }
}
