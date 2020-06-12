package com.cbr.university.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/rooms")
public class RoomRestController {
    private BaseService<Room> roomService;

    @Autowired
    public RoomRestController(BaseService<Room> roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        List<Room> rooms = roomService.getAll();

        if (rooms != null && !rooms.isEmpty()) {
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Room room) {
        roomService.create(room);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Room room) {
        roomService.update(room);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        roomService.delete(roomService.getById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
