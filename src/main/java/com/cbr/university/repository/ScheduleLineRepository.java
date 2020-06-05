package com.cbr.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cbr.university.model.ScheduleLine;

public interface ScheduleLineRepository extends JpaRepository<ScheduleLine, Integer> {

}
