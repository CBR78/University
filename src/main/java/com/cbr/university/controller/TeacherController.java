package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.Teacher;
import com.cbr.university.service.impl.CourseServiceImpl;
import com.cbr.university.service.impl.TeacherServiceImpl;

@Controller
@RequestMapping("teachers")
public class TeacherController {
    private static final String TEACHERS = "teachers";
    private static final String TEACHERS_ADD = "teacher-add";
    private static final String TEACHERS_EDIT = "teacher-edit";
    private ModelAndView mv = new ModelAndView();
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;
    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName(TEACHERS);
        mv.addObject(TEACHERS, teacherServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName(TEACHERS_ADD);
        mv.addObject("courses", courseServiceImpl.getAll());
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView add(Teacher teacher, BindingResult result) {
        teacherServiceImpl.create(teacher);
        return getAll();
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        mv.clear();
        mv.setViewName(TEACHERS_EDIT);
        mv.addObject("teacher", teacherServiceImpl.getById(id));
        mv.addObject("courses", courseServiceImpl.getAll());
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(Teacher teacher, BindingResult result) {
        teacherServiceImpl.update(teacher);
        return getAll();
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        teacherServiceImpl.delete(teacherServiceImpl.getById(id));
        return getAll();
    }
}
