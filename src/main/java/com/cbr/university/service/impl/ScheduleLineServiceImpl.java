package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.impl.ScheduleLineDaoImpl;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.BaseService;

@Service
public class ScheduleLineServiceImpl implements BaseService<ScheduleLine> {

    private ScheduleLineDaoImpl scheduleLineDaoImpl;

    @Autowired
    public ScheduleLineServiceImpl(ScheduleLineDaoImpl scheduleLineDaoImpl) {
        this.scheduleLineDaoImpl = scheduleLineDaoImpl;
    }

    @Override
    public void create(ScheduleLine scheduleLine) {
        scheduleLineDaoImpl.create(scheduleLine);
    }

    @Override
    public void update(ScheduleLine scheduleLine) {
        scheduleLineDaoImpl.update(scheduleLine);
    }

    @Override
    public void delete(ScheduleLine scheduleLine) {
        scheduleLineDaoImpl.delete(scheduleLine);
    }

    @Override
    public List<ScheduleLine> getAll() {
        return scheduleLineDaoImpl.getAll();
    }

    @Override
    public ScheduleLine getById(int id) {
        return scheduleLineDaoImpl.getById(id);
    }
}
