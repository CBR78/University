package com.cbr.university.service;

import com.cbr.university.model.ScheduleLine;
import com.cbr.university.repository.ScheduleLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleLineServiceImpl implements BaseService<ScheduleLine> {
    private final ScheduleLineRepository scheduleLineRepository;
    private final EntityManager entityManager;

    @Override
    public ScheduleLine create(ScheduleLine scheduleLine) {
        return scheduleLineRepository.save(scheduleLine);
    }

    @Override
    public ScheduleLine update(ScheduleLine scheduleLine) {
        return scheduleLineRepository.save(scheduleLine);
    }

    @Override
    public void deleteById(int id) {
        scheduleLineRepository.deleteById(id);
    }

    @Override
    public List<ScheduleLine> getAll() {
        entityManager.clear();
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
