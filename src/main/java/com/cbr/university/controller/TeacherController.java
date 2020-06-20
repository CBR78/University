package com.cbr.university.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.Course;
import com.cbr.university.model.Teacher;
import com.cbr.university.service.BaseService;

@Controller
@RequestMapping("teachers")
public class TeacherController {
    private static final String TEACHERS = "teachers";
    private static final String TEACHERS_ADD = "teacher-add";
    private static final String TEACHERS_EDIT = "teacher-edit";
    private BaseService<Teacher> teacherService;
    private BaseService<Course> courseService;
    private EntityManager entityManager;
    private ModelAndView mv = new ModelAndView();

    @Autowired
    public TeacherController(BaseService<Teacher> teacherService, BaseService<Course> courseService,
            EntityManager entityManager) {
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public ModelAndView getAll() {
        entityManager.clear();
        mv.clear();
        mv.setViewName(TEACHERS);
        mv.addObject(TEACHERS, teacherService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName(TEACHERS_ADD);
        mv.addObject("courses", courseService.getAll());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(Teacher teacher, BindingResult result) {
        teacherService.create(teacher);
        return getAll();
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName(TEACHERS_EDIT);
        mv.addObject("teacher", teacherService.getById(id));
        mv.addObject("courses", courseService.getAll());
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(Teacher teacher) {
        teacherService.update(teacher);
        return getAll();
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        teacherService.delete(teacherService.getById(id));
        return getAll();
    }
}
