package com.cbr.university.service;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.repository.ScheduleLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleLineServiceImpl implements BaseService<ScheduleLine> {
    private final ScheduleLineRepository scheduleLineRepository;

    @Override
    public ScheduleLine create(ScheduleLine scheduleLine) {
        return scheduleLineRepository.saveAndFlush(scheduleLine);
    }

    @Override
    public ScheduleLine update(ScheduleLine scheduleLine) {
        return scheduleLineRepository.saveAndFlush(scheduleLine);
    }

    @Override
    public void deleteById(int id) {
        scheduleLineRepository.deleteById(id);
    }

    @Override
    public List<ScheduleLine> getAll() {
        return scheduleLineRepository.findAll();
    }

    @Override
    public ScheduleLine getById(int id) {
        return scheduleLineRepository.findById(id).get();
    }

    @Override
    public boolean existsById(int id) {
        return scheduleLineRepository.existsById(id);
    }
}
