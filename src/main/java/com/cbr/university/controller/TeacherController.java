package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.Teacher;
import com.cbr.university.service.impl.TeacherServiceImpl;

@Controller
@RequestMapping("teachers")
public class TeacherController {
    private static final String TEACHERS = "teachers";
    private static final String TEACHERS_ADD = "teacher-add";
    private static final String TEACHERS_EDIT = "teacher-edit";
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(TEACHERS);
        mv.addObject(TEACHERS, teacherServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(TEACHERS_ADD);
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView add(String name) {
        Teacher teacher = new Teacher();
        // teacher.setName(name);
        teacherServiceImpl.create(teacher);

        ModelAndView mv = new ModelAndView();
        mv.setViewName(TEACHERS);
        mv.addObject(TEACHERS, teacherServiceImpl.getAll());
        return mv;
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(TEACHERS_EDIT);
        mv.addObject("teacher", teacherServiceImpl.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, Teacher teacher) {
        teacherServiceImpl.update(teacher);

        ModelAndView mv = new ModelAndView();
        mv.setViewName(TEACHERS);
        mv.addObject(TEACHERS, teacherServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        teacherServiceImpl.delete(teacherServiceImpl.getById(id));

        ModelAndView mv = new ModelAndView();
        mv.setViewName(TEACHERS);
        mv.addObject(TEACHERS, teacherServiceImpl.getAll());
        return mv;
    }
}
