package com.cbr.university.service;

import com.cbr.university.model.Group;
import com.cbr.university.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements BaseService<Group> {
    private final GroupRepository groupRepository;

    @Override
    public Group create(Group group) {
        return groupRepository.saveAndFlush(group);
    }

    @Override
    public Group update(Group group) {
        return groupRepository.saveAndFlush(group);
    }

    @Override
    public void deleteById(int id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<Group> getAll() {
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
