package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.ScheduleLineRepositroy;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.BaseService;

@Service
public class ScheduleLineServiceImpl implements BaseService<ScheduleLine> {

    private ScheduleLineRepositroy scheduleLineDao;

    @Autowired
    public ScheduleLineServiceImpl(ScheduleLineRepositroy scheduleLineDao) {
        this.scheduleLineDao = scheduleLineDao;
    }

    @Override
    public void create(ScheduleLine scheduleLine) {
        scheduleLineDao.saveAndFlush(scheduleLine);
    }

    @Override
    public void update(ScheduleLine scheduleLine) {
        scheduleLineDao.saveAndFlush(scheduleLine);
    }

    @Override
    public void delete(ScheduleLine scheduleLine) {
        scheduleLineDao.delete(scheduleLine);
    }

    @Override
    public List<ScheduleLine> getAll() {
        return (List<ScheduleLine>) scheduleLineDao.findAll();
    }

    @Override
    public ScheduleLine getById(int id) {
        return scheduleLineDao.findById(id).get();
    }
}
