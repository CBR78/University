package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.impl.GroupDaoImpl;
import com.cbr.university.model.Group;
import com.cbr.university.service.BaseService;

@Service
public class GroupServiceImpl implements BaseService<Group> {

    private GroupDaoImpl groupDaoImpl;

    @Autowired
    public GroupServiceImpl(GroupDaoImpl groupDaoImpl) {
        this.groupDaoImpl = groupDaoImpl;
    }

    @Override
    public void create(Group group) {
        groupDaoImpl.create(group);
    }

    @Override
    public void update(Group group) {
        groupDaoImpl.update(group);
    }

    @Override
    public void delete(Group group) {
        groupDaoImpl.delete(group);
    }

    @Override
    public List<Group> getAll() {
        return groupDaoImpl.getAll();
    }

    @Override
    public Group getById(int id) {
        return groupDaoImpl.getById(id);
    }
}
