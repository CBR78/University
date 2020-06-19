package com.cbr.university.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;

@RestController
@RequestMapping("rest/rooms")
public class RoomRestController {
    private static final String NAME_CUSTOM_HEADER = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    private BaseService<Room> roomService;

    @Autowired
    public RoomRestController(BaseService<Room> roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        List<Room> rooms = roomService.getAll();
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "All objects Room found. Number of objects " + rooms.size());
        return new ResponseEntity<>(rooms, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Room> add(@RequestBody Room room) {
        Room createdRoom = roomService.create(room);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Created Room object with id " + createdRoom.getId());
        return new ResponseEntity<>(createdRoom, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Room> update(@RequestBody Room room) {
        Room updatedRoom = roomService.update(room);
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Updated Room object with id " + updatedRoom.getId());
        return new ResponseEntity<>(updatedRoom, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Room> delete(@PathVariable("id") int id) {
        roomService.delete(roomService.getById(id));
        headers.clear();
        headers.add(NAME_CUSTOM_HEADER, "Deleted Room object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
