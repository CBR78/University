package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.repository.ScheduleLineRepository;
import com.cbr.university.service.BaseService;

@Service
public class ScheduleLineServiceImpl implements BaseService<ScheduleLine> {

    private ScheduleLineRepository scheduleLineRepository;

    @Autowired
    public ScheduleLineServiceImpl(ScheduleLineRepository scheduleLineRepository) {
        this.scheduleLineRepository = scheduleLineRepository;
    }

    @Override
    public ScheduleLine create(ScheduleLine scheduleLine) {
        return scheduleLineRepository.save(scheduleLine);
    }

    @Override
    public ScheduleLine update(ScheduleLine scheduleLine) {
        return scheduleLineRepository.save(scheduleLine);
    }

    @Override
    public void delete(ScheduleLine scheduleLine) {
        scheduleLineRepository.delete(scheduleLine);
    }

    @Override
    public List<ScheduleLine> getAll() {
        return scheduleLineRepository.findAll();
    }

    @Override
    public ScheduleLine getById(int id) {
        return scheduleLineRepository.findById(id).get();
    }
}
