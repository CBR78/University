package com.cbr.university.controller.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import com.cbr.university.model.dto.RoomDtoRest;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;

@RestController
@RequestMapping("rest/rooms")
@Validated
public class RoomRestController {
    private static final String CUSTOM_HEADER_NAME = "X-Query-Result";
    private HttpHeaders headers = new HttpHeaders();
    private ModelMapper modelMapper;
    private BaseService<Room> roomService;

    @Autowired
    public RoomRestController(BaseService<Room> roomService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        List<Room> rooms = roomService.getAll();
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "All objects Room found. Number of objects " + rooms.size());
        return new ResponseEntity<>(rooms, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Room> add(@Validated(Create.class) @RequestBody RoomDtoRest roomDtoRest) {
        Room room = modelMapper.map(roomDtoRest, Room.class);
        Room createdRoom = roomService.create(room);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Created Room object with id " + createdRoom.getId());
        return new ResponseEntity<>(createdRoom, headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Room> update(@Validated(Update.class) @RequestBody RoomDtoRest roomDtoRest) {
        Room room = modelMapper.map(roomDtoRest, Room.class);
        Room updatedRoom = roomService.update(room);
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Updated Room object with id " + updatedRoom.getId());
        return new ResponseEntity<>(updatedRoom, headers, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Room> delete(
            @NotNull(message = "Request must include a Room id") @IdExistsInDb(typeObject = "Room", message = "This Room id is not in the database") @PathVariable Integer id) {
        roomService.delete(roomService.getById(id));
        headers.clear();
        headers.add(CUSTOM_HEADER_NAME, "Deleted Room object with id " + id);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
