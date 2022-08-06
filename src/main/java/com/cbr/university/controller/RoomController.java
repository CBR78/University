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

    @GetMapping("get-add-view")
    public String getAddView(Model model) {
        model.addAttribute(new Room());
        return "editing/rooms/add";
    }

    @PostMapping("save-add-view")
    public String saveAddView(@Validated(RequestUI.class) Room room, BindingResult result) {
        if (!result.hasErrors()) {
            roomService.create(room);
            return "redirect:/editing/rooms";
        } else {
            return "redirect:/editing/rooms/get-add-view";
        }
    }

    @GetMapping("get-edit-view/{id}")
    public String getEditView(@PathVariable int id, Model model) {
        model.addAttribute(roomService.getById(id));
        return "editing/rooms/edit";
    }

    @PostMapping("save-edit-view")
    public String saveEditView(@Validated(RequestUI.class) Room room, BindingResult result) {
        if (!result.hasErrors()) {
            roomService.update(room);
            return "redirect:/editing/rooms";
        } else {
            return "redirect:/editing/rooms/get-edit-view/{id}";
        }
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        roomService.deleteById(id);
        return "redirect:/editing/rooms";
    }
}
