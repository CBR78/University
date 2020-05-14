package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.Room;
import com.cbr.university.service.impl.RoomServiceImpl;

@Controller
@RequestMapping("rooms")
public class RoomController {
    private static final String ROOMS = "rooms";
    private static final String ROOMS_ADD = "room-add";
    private static final String ROOMS_EDIT = "room-edit";
    private ModelAndView mv = new ModelAndView();
    @Autowired
    private RoomServiceImpl roomServiceImpl;

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName(ROOMS);
        mv.addObject(ROOMS, roomServiceImpl.getAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName(ROOMS_ADD);
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView add(Room room, BindingResult result) {
        roomServiceImpl.create(room);
        return getAll();
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        mv.clear();
        mv.setViewName(ROOMS_EDIT);
        mv.addObject("room", roomServiceImpl.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, Room room) {
        roomServiceImpl.update(room);
        return getAll();
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        roomServiceImpl.delete(roomServiceImpl.getById(id));
        return getAll();
    }
}
