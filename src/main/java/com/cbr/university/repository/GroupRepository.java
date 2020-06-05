package com.cbr.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

}
