package com.cbr.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.Group;

public interface GroupRepositroy extends JpaRepository<Group, Integer> {

}
