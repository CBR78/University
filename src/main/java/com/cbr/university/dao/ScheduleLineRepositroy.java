package com.cbr.university.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.ScheduleLine;

public interface ScheduleLineRepositroy extends JpaRepository<ScheduleLine, Integer> {

}
