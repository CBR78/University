package com.cbr.university.service.impl;

import com.cbr.university.model.Group;
import com.cbr.university.repository.GroupRepository;
import com.cbr.university.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class GroupServiceImpl implements BaseService<Group> {

    private final GroupRepository groupRepository;
    private final EntityManager entityManager;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, EntityManager entityManager) {
        this.groupRepository = groupRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group update(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public List<Group> getAll() {
        entityManager.clear();
        return groupRepository.findAll();
    }

    @Override
    public Group getById(int id) {
        return groupRepository.findById(id).get();
    }

    @Override
    public boolean existsById(int id) {
        return groupRepository.existsById(id);
    }
}
