package com.cbr.university.service;

import com.cbr.university.model.Course;
import com.cbr.university.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements BaseService<Course> {
    private final CourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public Course update(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public void deleteById(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public boolean existsById(int id) {
        return courseRepository.existsById(id);
    }
}
