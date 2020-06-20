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

import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;

@Controller
@RequestMapping("rooms")
public class RoomController {
    private static final String ROOMS = "rooms";
    private static final String ROOMS_ADD = "room-add";
    private static final String ROOMS_EDIT = "room-edit";
    private BaseService<Room> roomService;
    private EntityManager entityManager;
    private ModelAndView mv = new ModelAndView();

    @Autowired
    public RoomController(BaseService<Room> roomService, EntityManager entityManager) {
        this.roomService = roomService;
        this.entityManager = entityManager;
    }

    @GetMapping
    public ModelAndView getAll() {
        entityManager.clear();
        mv.clear();
        mv.setViewName(ROOMS);
        mv.addObject(ROOMS, roomService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName(ROOMS_ADD);
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(Room room, BindingResult result) {
        roomService.create(room);
        return getAll();
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName(ROOMS_EDIT);
        mv.addObject("room", roomService.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(Room room) {
        roomService.update(room);
        return getAll();
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        roomService.delete(roomService.getById(id));
        return getAll();
    }
}
