package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.GroupRepositroy;
import com.cbr.university.model.Group;
import com.cbr.university.service.BaseService;

@Service
public class GroupServiceImpl implements BaseService<Group> {

    private GroupRepositroy groupDao;

    @Autowired
    public GroupServiceImpl(GroupRepositroy groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void create(Group group) {
        groupDao.saveAndFlush(group);
    }

    @Override
    public void update(Group group) {
        groupDao.saveAndFlush(group);
    }

    @Override
    public void delete(Group group) {
        groupDao.delete(group);
    }

    @Override
    public List<Group> getAll() {
        return (List<Group>) groupDao.findAll();
    }

    @Override
    public Group getById(int id) {
        return groupDao.findById(id).get();
    }
}
