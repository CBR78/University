package com.cbr.university.controller;

import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.group.RequestUI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("editing/rooms")
@Validated
public class RoomController {
    private final BaseService<Room> roomService;

    public RoomController(BaseService<Room> roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(roomService.getAll());
        return "editing/rooms/view";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute(Room.class);
        return "editing/rooms/add";
    }

    @PostMapping("add")
    public String add(@Validated(RequestUI.class) Room room, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute(room);
            return "editing/rooms/add";
        } else {
            roomService.create(room);
            return getAll(model);
        }
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute(roomService.getById(id));
        return "editing/rooms/edit";
    }

    @PostMapping("edit")
    public String edit(@Validated(RequestUI.class) Room room, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute(room);
            return "editing/rooms/edit";
        } else {
            roomService.update(room);
            return getAll(model);
        }
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        roomService.deleteById(id);
        return getAll(model);
    }
}
