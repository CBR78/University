package com.cbr.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;

@Controller
@RequestMapping("rooms")
@Validated
public class RoomController {
    private ModelAndView mv = new ModelAndView();
    private BaseService<Room> roomService;

    @Autowired
    public RoomController(BaseService<Room> roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ModelAndView getAll() {
        mv.clear();
        mv.setViewName("rooms");
        mv.addObject("rooms", roomService.getAll());
        return mv;
    }

    @GetMapping("add")
    public ModelAndView add() {
        mv.clear();
        mv.setViewName("room-add");
        mv.addObject("room", new Room());
        return mv;
    }

    @PostMapping("add")
    public ModelAndView add(@Validated(RequestUI.class) Room room, BindingResult result) {

        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("room-add");
            mv.addObject("room", room);
            return mv;
        } else {
            roomService.create(room);
            return getAll();
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        mv.clear();
        mv.setViewName("room-edit");
        mv.addObject("room", roomService.getById(id));
        return mv;
    }

    @PostMapping("edit/{id}")
    public ModelAndView edit(@Validated(RequestUI.class) Room room, BindingResult result) {

        if (result.hasErrors()) {
            mv.clear();
            mv.setViewName("room-edit");
            mv.addObject("room", room);
            return mv;
        } else {
            roomService.update(room);
            return getAll();
        }
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        roomService.delete(roomService.getById(id));
        return getAll();
    }
}
