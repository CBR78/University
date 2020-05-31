package com.cbr.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbr.university.dao.BaseDao;
import com.cbr.university.model.ScheduleLine;
import com.cbr.university.service.BaseService;

@Service
public class ScheduleLineServiceImpl implements BaseService<ScheduleLine> {

    private BaseDao<ScheduleLine> scheduleLineDao;

    @Autowired
    public ScheduleLineServiceImpl(BaseDao<ScheduleLine> scheduleLineDao) {
        this.scheduleLineDao = scheduleLineDao;
    }

    @Override
    public void create(ScheduleLine scheduleLine) {
        scheduleLineDao.create(scheduleLine);
    }

    @Override
    public void update(ScheduleLine scheduleLine) {
        scheduleLineDao.update(scheduleLine);
    }

    @Override
    public void delete(ScheduleLine scheduleLine) {
        scheduleLineDao.delete(scheduleLine);
    }

    @Override
    public List<ScheduleLine> getAll() {
        return scheduleLineDao.getAll();
    }

    @Override
    public ScheduleLine getById(int id) {
        return scheduleLineDao.getById(id);
    }
}
