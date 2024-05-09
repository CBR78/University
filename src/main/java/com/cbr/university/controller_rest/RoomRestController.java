package com.cbr.university.controller_rest;

import com.cbr.university.model.Room;
import com.cbr.university.service.BaseService;
import com.cbr.university.validation.IdExistsInDb;
import com.cbr.university.validation.group.Create;
import com.cbr.university.validation.group.Update;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("rest/rooms")
@Validated
public class RoomRestController {
    private final BaseService<Room> roomService;

    @GetMapping
    public List<Room> getAll() {
        return roomService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room add(@Validated(Create.class) @RequestBody Room room) {
        return roomService.create(room);
    }

    @PutMapping
    public Room update(@Validated(Update.class) @RequestBody Room room) {
        return roomService.update(room);
    }

    @DeleteMapping("{id}")
    public void delete(
            @NotNull(message = "Request must include a Room id")
            @IdExistsInDb(typeObject = "Room", message = "This Room id is not in the database")
            @PathVariable Integer id) {
        roomService.deleteById(id);
    }
}
